package com.hcindy.chickenfeetsvc.service

import com.hcindy.chickenfeetsvc.dto.AccountDTO

interface AuthService {
    fun login(accountDTO: AccountDTO): String
    fun logout(token: String)
    fun getAccountUsername(token: String): String?
}
