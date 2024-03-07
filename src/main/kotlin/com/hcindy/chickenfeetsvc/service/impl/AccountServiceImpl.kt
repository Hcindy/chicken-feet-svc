package com.hcindy.chickenfeetsvc.service.impl

import com.hcindy.chickenfeetsvc.constant.LOGINED_LIFE_TIME
import com.hcindy.chickenfeetsvc.dto.AccountDTO
import com.hcindy.chickenfeetsvc.service.AccountService
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class AccountServiceImpl(private val stringRedisTemplate: StringRedisTemplate) : AccountService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun createAccount(accountDTO: AccountDTO): Boolean {
        // FIXME store to db
        val res = stringRedisTemplate
            .opsForValue()
            .setIfAbsent(
                "account:${accountDTO.username}:username",
                "", // game ids
                LOGINED_LIFE_TIME.toLong(),
                TimeUnit.SECONDS
            )
        return res ?: false
    }

    override fun existAccount(accountDTO: AccountDTO): Boolean =
        // FIXME find in db
        stringRedisTemplate
            .hasKey("account:${accountDTO.username}:username")

    override fun getAccountJoinedGameId(username: String): String? =
        stringRedisTemplate
            .opsForValue()
            .get("account:${username}:username")

    override fun setJoinGame(username: String, gameId: String): Boolean =
        stringRedisTemplate
            .opsForValue()
            .setIfPresent(
                "account:${username}:username",
                gameId,
                LOGINED_LIFE_TIME.toLong(),
                TimeUnit.SECONDS
            )
            ?: false

    override fun deleteAccountJoinedGameId(username: String): Boolean =
        setJoinGame(username, "")

}
