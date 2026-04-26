package com.example.worldcupapp.domain.model

data class MatchScore(
    val runs: Int,
    val wickets: Int,
    val overs: Float,
    val target: Int? = null    
) {
    val runRate: Float
        get() = if (overs > 0) runs / overs else 0f
}
