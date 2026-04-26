package com.example.worldcupapp.domain.repository

import com.example.worldcupapp.domain.model.Player

interface PlayerRepository {
    suspend fun getPlayers(teamId: Int): List<Player>
}
