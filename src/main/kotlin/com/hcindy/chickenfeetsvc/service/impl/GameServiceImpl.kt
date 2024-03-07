package com.hcindy.chickenfeetsvc.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.hcindy.chickenfeetsvc.dto.*
import com.hcindy.chickenfeetsvc.exception.FailedException
import com.hcindy.chickenfeetsvc.service.AccountService
import com.hcindy.chickenfeetsvc.service.GameService
import com.hcindy.chickenfeetsvc.util.createUuid
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.RedisStringCommands
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.SessionCallback
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.types.Expiration
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class GameServiceImpl(
    private val jacksonObjectMapper: ObjectMapper,
    private val stringRedisTemplate: StringRedisTemplate,
    private val accountService: AccountService
) : GameService {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val nullStrBA = "".toByteArray()
    private val keepAlive = 3600L

    override fun getGameList(): List<GameDTO> {
        return listOf(
            GameDTO("a", "b", emptyList(), listOf(ParticipantDTO(AccountDTO("x"), 0)), 0, 0),
            GameDTO(
                "q",
                "w",
                emptyList(),
                listOf(ParticipantDTO(AccountDTO("e"), 0), ParticipantDTO(AccountDTO("i"), 0)),
                0,
                0
            )
        )
    }

    override fun getNewGameList(): List<GameDTO> {
        val keys = stringRedisTemplate.keys("newgames:*:id")
        val epRes: List<Any> = stringRedisTemplate.executePipelined(
            {
                for (key in keys) {
                    val keyBA = key.toByteArray()
                    it.hashCommands()
                        .hMGet(keyBA, "name".toByteArray(), "startTime".toByteArray(), "endTime".toByteArray())
                    it.setCommands().sMembers(keyBA.plus(":bonuses".toByteArray()))
                    it.setCommands().sMembers(keyBA.plus(":participants".toByteArray()))
                }
                null
            },
            RedisSerializer.string()
        )
        logger.info(epRes.toString())
        return keys.mapIndexedNotNull { index, key ->
            val i = index * 3
            val obj = epRes[i] as List<Any?>
            if (obj[0] == null) {
                return@mapIndexedNotNull null
            } else {
                val name = obj[0] as String
                val startTimeStr = obj[1] as String?
                val endTimeStr = obj[2] as String?
                val b = (epRes[i + 1] as Set<String>).map {
                    jacksonObjectMapper.readValue<BonusDTO>(it)
                }
                val p = (epRes[i + 2] as Set<String>).map {
                    jacksonObjectMapper.readValue<ParticipantDTO>(it)
                }
                GameDTO(key.split(":")[1], name, b, p, startTimeStr?.toLong(), endTimeStr?.toLong())
            }
        }
    }

    // TODO 考虑try catch包裹处理错误情况，考虑接executePipelined返回值处理错误情况
    override fun createNewGame(gameDTO: GameDTO): Boolean {
        gameDTO.id = createUuid()
        val gameIdKey = "newgames:${gameDTO.id}:id".toByteArray()
        val gameIdValue = mapOf(
            "name".toByteArray() to gameDTO.name.toByteArray(),
            "startTime".toByteArray() to gameDTO.startTime?.toString()?.toByteArray(),
            "endTime".toByteArray() to gameDTO.endTime?.toString()?.toByteArray()
        )
        val gameBonusesKey = "newgames:${gameDTO.id}:id:bonuses".toByteArray()
        val gameBonusesValue = gameDTO.bonuses.map { b -> jacksonObjectMapper.writeValueAsBytes(b) }.toTypedArray()
        // Participants在新游戏的时候为空，redis不支持，这部分延迟到有用户加入的时候再创建
//        val gameParticipantsKey = "newgames:${gameDTO.id}:id:participants".toByteArray()
//        val gameParticipantsValue = gameDTO.participants.map { p -> jacksonObjectMapper.writeValueAsBytes(p) }.toTypedArray()
        val unChosenBonuses = mutableListOf<Map<String, String>>()
        gameDTO.bonuses.forEach { b ->
            repeat(b.amount) {
                unChosenBonuses.add(mapOf("name" to b.name, "worth" to b.worth.toString()))
            }
        }
        val shuffled = unChosenBonuses.shuffled().shuffled().shuffled()
        stringRedisTemplate.executePipelined(
            {
                it.multi()
                it.hashCommands().hMSet(gameIdKey, gameIdValue)
                it.expire(gameIdKey, keepAlive)
                it.setCommands().sAdd(gameBonusesKey, *gameBonusesValue)
                it.expire(gameBonusesKey, keepAlive)
                // Participants在新游戏的时候为空，redis不支持，这部分延迟到有用户加入的时候再创建
//                it.setCommands().sAdd(gameParticipantsKey, *gameParticipantsValue)
//                it.expire(gameParticipantsKey, keepAlive)
                shuffled.forEachIndexed { index, s ->
                    it.set(
                        "games:${gameDTO.id}:id:${index}:unChosenIndex".toByteArray(),
                        nullStrBA,
                        Expiration.seconds(keepAlive),
                        RedisStringCommands.SetOption.SET_IF_ABSENT
                    )
                    val k = "games:${gameDTO.id}:id:${index}:chosenBonuses".toByteArray()
                    it.hashCommands().hMSet(
                        k,
                        mapOf(
                            "${index}-name".toByteArray() to s["name"]!!.toByteArray(),
                            "${index}-worth".toByteArray() to s["worth"]!!.toByteArray()
                        )
                    )
//                    it.expire(k, keepAlive)
                }
                it.exec()
                null
            },
            RedisSerializer.string()
        )
        return true
    }

    override fun setStartTimeAfter(id: String, startTime: Int): Boolean {
        val newST = System.currentTimeMillis() + startTime * 1000
        stringRedisTemplate
            .opsForHash<String, String>()
            .put("newgames:${id}:id", "startTime", newST.toString())
        return true
    }

    override fun getGame(id: String): GameDTO {
        val epRes: List<Any> = stringRedisTemplate.executePipelined(
            {
                val keyBA = "newgames:${id}:id".toByteArray()
                it.hashCommands()
                    .hMGet(keyBA, "name".toByteArray(), "startTime".toByteArray(), "endTime".toByteArray())
                it.setCommands().sMembers(keyBA.plus(":bonuses".toByteArray()))
                it.setCommands().sMembers(keyBA.plus(":participants".toByteArray()))
                null
            },
            RedisSerializer.string()
        )
        if (epRes.size != 3) throw FailedException("数据异常")
        else {
            val obj = epRes[0] as List<Any?>
            val name = obj[0] as String
            val startTimeStr = obj[1] as String?
            val endTimeStr = obj[2] as String?
            val b = (epRes[1] as Set<String>).map {
                jacksonObjectMapper.readValue<BonusDTO>(it)
            }
            val p = (epRes[2] as Set<String>).map {
                jacksonObjectMapper.readValue<ParticipantDTO>(it)
            }
            return GameDTO(id, name, b, p, startTimeStr?.toLong(), endTimeStr?.toLong())
        }
    }

    override fun getOveredGame(id: String): List<BonusOwnerDTO> =
        stringRedisTemplate
            .keys("games:${id}:id:*:chosenBonuses")
            .sortedBy { it.split(":")[3].toInt() }
            .let { keys ->
                val epRes: List<Any> = stringRedisTemplate.executePipelined(
                    {
                        keys.forEachIndexed { index, key ->
                            it.hashCommands()
                                .hMGet(key.toByteArray(),
                                    "${index}-name".toByteArray(),
                                    "${index}-worth".toByteArray(),
                                    "${index}-username".toByteArray(),
                                    "${index}-time".toByteArray())
                        }
                        null
                    },
                    RedisSerializer.string()
                )
                logger.info(epRes.toString())
                epRes.map {
                    val b = it as List<String?>
                    BonusOwnerDTO(b[0]!!, b[1]!!.toDouble(), b[2], b[3]?.toLong())
                }
            }

    // FIXME 检查两项操作结果，有错误则回滚，或者两项操作合二为一
    override fun appendParticipant(id: String, accountUsername: String): Boolean {
        val res = stringRedisTemplate
            .opsForSet()
            .add(
                "newgames:${id}:id:participants",
                jacksonObjectMapper.writeValueAsString(
                    ParticipantDTO(AccountDTO(accountUsername), System.currentTimeMillis())
                )
            )
            ?.let {
                accountService.setJoinGame(accountUsername, id)
            }
        stringRedisTemplate.expire("newgames:${id}:id:participants", keepAlive, TimeUnit.SECONDS)
        return res != null
    }

    override fun removeParticipant(id: String, accountUsername: String): Boolean {
        return stringRedisTemplate
            .opsForSet()
            .run {
                members("newgames:${id}:id:participants")
                    ?.find {
                        it.contains("{\"username\":\"${accountUsername}\"}")
                    }
                    ?.let {
                        remove(
                            "newgames:${id}:id:participants",
                            it
                        )
                    }
                    ?.let {
                        it > 0
                    }
                    ?: false
            }
    }

    override fun getGameAvailableBonuses(id: String): List<Int> =
        stringRedisTemplate
            .keys("games:${id}:id:*:unChosenIndex")
            .sortedBy { it.split(":")[3].toInt() }
            .map { it.split(":")[3].toInt() }

    override fun chooseBonusIndex(id: String, index: Int, accountUsername: String) {
        val chooser = stringRedisTemplate.hasKey("games:${id}:id:${accountUsername}:chooser")
        if (chooser) throw FailedException("exist")

        val delete = stringRedisTemplate.delete("games:${id}:id:${index}:unChosenIndex")
        if (!delete) throw FailedException("to late")

        val epRes: List<Any> = stringRedisTemplate.executePipelined(
            {
                it.multi()
                it.set(
                    "games:${id}:id:${accountUsername}:chooser".toByteArray(),
                    nullStrBA,
                    Expiration.seconds(keepAlive),
                    RedisStringCommands.SetOption.SET_IF_ABSENT
                )
                it.hashCommands().hMSet(
                    "games:${id}:id:${index}:chosenBonuses".toByteArray(),
                    mapOf(
                        "${index}-username".toByteArray() to accountUsername.toByteArray(),
                        "${index}-time".toByteArray() to System.currentTimeMillis().toString().toByteArray()
                    )
                )
                it.exec()
                null
            },
            RedisSerializer.string()
        )
        logger.info(epRes.toString())
        val s = epRes[0] as List<Boolean>
        if (!s[0]) throw FailedException("mark fail")
    }

}
