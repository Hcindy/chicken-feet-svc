package com.hcindy.chickenfeetsvc.service

import com.hcindy.chickenfeetsvc.dto.BonusOwnerDTO
import com.hcindy.chickenfeetsvc.dto.GameDTO

interface GameService {
    fun getGameList(): List<GameDTO>
    fun getNewGameList(): List<GameDTO>
    fun createNewGame(gameDTO: GameDTO): Boolean
    fun setStartTimeAfter(id: String, startTime: Int): Boolean
    fun getGame(id: String): GameDTO
    fun getOveredGame(id: String): List<BonusOwnerDTO>
    fun appendParticipant(id: String, accountUsername: String): Boolean
    fun removeParticipant(id: String, accountUsername: String): Boolean
    fun getGameAvailableBonuses(id: String): List<Int>
    fun chooseBonusIndex(id: String, index: Int, accountUsername: String)
}
