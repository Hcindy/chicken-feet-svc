package com.hcindy.chickenfeetsvc.service

import com.hcindy.chickenfeetsvc.dto.AccountDTO

interface AccountService {
    fun createAccount(accountDTO: AccountDTO): Boolean
    fun existAccount(accountDTO: AccountDTO): Boolean
    fun getAccountJoinedGameId(username: String): String?
    fun setJoinGame(username: String, gameId: String): Boolean
    fun deleteAccountJoinedGameId(username: String): Boolean
}
