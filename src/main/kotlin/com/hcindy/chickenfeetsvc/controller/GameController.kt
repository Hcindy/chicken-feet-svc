package com.hcindy.chickenfeetsvc.controller

import com.hcindy.chickenfeetsvc.dto.*
import com.hcindy.chickenfeetsvc.service.AccountService
import com.hcindy.chickenfeetsvc.service.AuthService
import com.hcindy.chickenfeetsvc.service.GameService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/games")
@CrossOrigin
class GameController(
    private val gameService: GameService,
    private val authService: AuthService,
    private val accountService: AccountService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getGameList(): StandardResponseDTO<List<GameDTO>> =
        ok(gameService.getGameList())

    @GetMapping("new")
    fun getNewGameList(): StandardResponseDTO<List<GameDTO>> =
        ok(gameService.getNewGameList())

    @PostMapping
    fun postGame(@RequestBody gameDTO: GameDTO): StandardResponseDTO<Any> =
        if (gameService.createNewGame(gameDTO)) ok()
        else fail("exist")

    @PostMapping("{id}/startTime/{startTime}")
    fun postSetStartTimeAfter(
        @PathVariable id: String,
        @PathVariable startTime: Int
    ): StandardResponseDTO<Any> =
        if (gameService.setStartTimeAfter(id, startTime)) ok()
        else fail("fail")

    @GetMapping("{id}")
    fun getGame(@PathVariable id: String): StandardResponseDTO<GameDTO> =
        ok(gameService.getGame(id))

    @GetMapping("overed/{id}")
    fun getOveredGame(@PathVariable id: String): StandardResponseDTO<List<BonusOwnerDTO>> =
        ok(gameService.getOveredGame(id))

    @PostMapping("{id}/participants")
    fun postJoinGame(
        @PathVariable id: String,
        @RequestHeader(value = "Authorization") token: String?
    ): StandardResponseDTO<Any> =
        if (token != null) {
            val accountUsername = authService.getAccountUsername(token)
            if (accountUsername == null) {
                fail("not logged in")
            } else {
                // FIXME 用username拿account对象，判断里面的属性，是否存在已经加入的游戏
                val joinedGameId = accountService.getAccountJoinedGameId(accountUsername)
                if (joinedGameId == id) {
                    ok()
                } else if (joinedGameId == null || joinedGameId.isEmpty()) {
                    if (gameService.appendParticipant(id, accountUsername)) {
                        ok()
                    } else {
                        fail("join fail")
                    }
                } else {
                    fail("joined")
                }
            }
        } else {
            fail("not logged in")
        }

    @DeleteMapping("{id}/participants")
    fun deleteExitGame(
        @PathVariable id: String,
        @RequestHeader(value = "Authorization") token: String?
    ): StandardResponseDTO<Any> =
        if (token != null) {
            val accountUsername = authService.getAccountUsername(token)
            if (accountUsername == null) {
                fail("not logged in")
            } else {
                // FIXME 用username拿account对象，判断里面的属性，是否存在已经加入的游戏
                val deleteRes = accountService.deleteAccountJoinedGameId(accountUsername)
                if (deleteRes) {
                    if (gameService.removeParticipant(id, accountUsername)) {
                        ok()
                    } else {
                        fail("exit fail")
                    }
                } else {
                    fail("exit fail")
                }
            }
        } else {
            fail("not logged in")
        }

    @PostMapping("{id}/bonuses/{index}/choose")
    fun postChooseBonusIndex(
        @PathVariable id: String,
        @PathVariable index: Int,
        @RequestHeader(value = "Authorization") token: String?
    ): StandardResponseDTO<Any> =
        if (token != null) {
            val accountUsername = authService.getAccountUsername(token)
            if (accountUsername == null) {
                fail("not logged in")
            } else {
                gameService.chooseBonusIndex(id, index, accountUsername)
                ok()
            }
        } else {
            fail("not logged in")
        }

    @PostMapping("{id}/bonuses/{index}/chooser/{accountUsername}")
    fun postSetBonusChooser(
        @PathVariable id: String,
        @PathVariable index: Int,
        @PathVariable accountUsername: String,
    ): StandardResponseDTO<Any> {
        gameService.chooseBonusIndex(id, index, accountUsername)
        return ok()
    }

    @GetMapping("{id}/bonuses/available")
    fun getGameAvailableBonuses(
        @PathVariable id: String,
        @RequestHeader(value = "Authorization") token: String?
    ): StandardResponseDTO<Any> =
        if (token != null) {
            val accountUsername = authService.getAccountUsername(token)
            if (accountUsername == null) {
                fail("not logged in")
            } else {
                // TODO 判断id和token匹配
                ok(gameService.getGameAvailableBonuses(id))
            }
        } else {
            fail("not logged in")
        }

}
