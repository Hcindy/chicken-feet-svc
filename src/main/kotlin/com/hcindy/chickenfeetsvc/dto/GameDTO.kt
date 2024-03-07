package com.hcindy.chickenfeetsvc.dto

data class GameDTO(
    var id: String,
    var name: String,
    var bonuses: List<BonusDTO>,
    var participants: List<ParticipantDTO>,
    var startTime: Long?,
    var endTime: Long?
)
