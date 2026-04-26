package com.example.worldcupapp.domain.model

data class Team(
    val id: String,
    val name: String,
    val shortName: String,
    val flagUrl: String,
    val ranking: Int
)
