package com.hcindy.chickenfeetsvc.controller

import com.hcindy.chickenfeetsvc.constant.LOGINED_LIFE_TIME
import com.hcindy.chickenfeetsvc.dto.AccountDTO
import com.hcindy.chickenfeetsvc.dto.StandardResponseDTO
import com.hcindy.chickenfeetsvc.dto.ok
import com.hcindy.chickenfeetsvc.service.AuthService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("v1/auth")
@CrossOrigin
class AuthController(private val authService: AuthService) {
    @PostMapping("login")
    fun postLogin(
        @RequestBody accountDTO: AccountDTO,
        httpServletResponse: HttpServletResponse
    ): StandardResponseDTO<String> {
        val token = authService.login(accountDTO)
        httpServletResponse.addCookie(
            Cookie("token", token)
                .apply {
                    path = "/"
                    isHttpOnly = true
                    maxAge = LOGINED_LIFE_TIME
                }
        )
        return ok(token)
    }

    @PostMapping("logout")
    fun postLogout(
        @CookieValue(value = "token", defaultValue = "") token: String,
        httpServletResponse: HttpServletResponse
    ): StandardResponseDTO<Any> {
        if (token.isNotBlank()) {
            authService.logout(token)
        }
        httpServletResponse.addCookie(
            Cookie("token", null)
                .apply {
                    path = "/"
                    isHttpOnly = true
                    maxAge = 0
                }
        )
        return ok()
    }
}
