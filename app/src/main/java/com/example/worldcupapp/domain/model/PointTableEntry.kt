package com.example.worldcupapp.domain.model

data class PointTableEntry(
    val team: Team,
    val played: Int,
    val won: Int,
    val lost: Int,
    val tie: Int,
    val nrr: Float,
    val points: Int
)
