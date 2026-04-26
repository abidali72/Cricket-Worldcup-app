package com.example.worldcupapp.data.repository

import com.example.worldcupapp.data.remote.CricketApiService
import com.example.worldcupapp.domain.model.Player
import com.example.worldcupapp.domain.repository.PlayerRepository
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val api: CricketApiService
) : PlayerRepository {

    override suspend fun getPlayers(teamId: Int): List<Player> {
        return try {
            val response = api.getPlayers(teamId)
            response.data.map { dto ->
                Player(
                    id = dto.id,
                    name = dto.name,
                    role = dto.role,
                    imageUrl = dto.image
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
             emptyList()
        }
    }
}
