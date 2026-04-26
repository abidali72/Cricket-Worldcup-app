package com.example.worldcupapp.domain.model

data class Player(
    val id: Int,
    val name: String,
    val role: String?, // e.g., Batsman, Bowler
    val imageUrl: String? = null,
    val isCaptain: Boolean = false
)
