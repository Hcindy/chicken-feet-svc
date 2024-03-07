package com.hcindy.chickenfeetsvc.controller

import com.hcindy.chickenfeetsvc.dto.AccountDTO
import com.hcindy.chickenfeetsvc.dto.StandardResponseDTO
import com.hcindy.chickenfeetsvc.dto.fail
import com.hcindy.chickenfeetsvc.dto.ok
import com.hcindy.chickenfeetsvc.service.AccountService
import com.hcindy.chickenfeetsvc.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/accounts")
@CrossOrigin
class AccountController(
    private val accountService: AccountService,
    private val authService: AuthService
) {
    @PostMapping
    fun postAccount(@RequestBody accountDTO: AccountDTO): StandardResponseDTO<Any> =
        if (accountService.createAccount(accountDTO)) ok()
        else fail("exist")

    @GetMapping
    fun getAccountJoinedGameId(@RequestHeader(value = "Authorization") token: String?): StandardResponseDTO<Any> =
        if (token != null) {
            val accountUsername = authService.getAccountUsername(token)
            if (accountUsername != null) {
                ok(accountService.getAccountJoinedGameId(accountUsername))
            } else {
                fail("not logged in")
            }
        } else {
            fail("not logged in")
        }
}
