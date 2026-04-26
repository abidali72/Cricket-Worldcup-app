package com.example.worldcupapp.domain.repository

import com.example.worldcupapp.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getLiveMatches(): Flow<List<Match>>
    fun getAllMatches(): Flow<List<Match>>
    suspend fun getMatchDetails(matchId: String): Match?
}
