package com.example.worldcupapp.domain.model

enum class MatchStatus {
    UPCOMING, LIVE, COMPLETED, ABANDONED
}

data class Match(
    val id: String,
    val teamA: Team,
    val teamB: Team,
    val scoreA: MatchScore? = null,
    val scoreB: MatchScore? = null,
    val status: MatchStatus,
    val venue: String,
    val startTime: Long,
    val winningSummary: String? = null
)
