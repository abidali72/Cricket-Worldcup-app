package com.example.worldcupapp.data.repository

import com.example.worldcupapp.data.remote.MockLiveDataSource
import com.example.worldcupapp.domain.model.Match
import com.example.worldcupapp.domain.repository.MatchRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val dataSource: MockLiveDataSource,
    private val api: com.example.worldcupapp.data.remote.CricketApiService
) : MatchRepository {

    override fun getLiveMatches(): Flow<List<Match>> = flow {
        // Emit mock data immediately as cache/placeholder
        emit(dataSource.getLiveMatchStream().first())
        
        while (true) {
            try {
                val response = api.getLiveScores()
                if (response.data != null) {
                    val matches = response.data.map { dto ->
                        dto.toDomainMatch()
                    }
                    emit(matches)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // On error, we might want to emit nothing or keep previous state
                // For now, just log and wait
            }
            delay(10000) // Poll every 10 seconds
        }
    }

    override fun getAllMatches(): Flow<List<Match>> = flow {
        // In a real app, this might come from Room or API
        emit(dataSource.getAllMatches())
    }

    override suspend fun getMatchDetails(matchId: String): Match? {
        return dataSource.getAllMatches().find { it.id == matchId }
    }

    private fun com.example.worldcupapp.data.remote.dto.LiveScoreDto.toDomainMatch(): Match {
        // Helper to parse "245/7" -> MatchScore
        fun parseScore(scoreStr: String?): com.example.worldcupapp.domain.model.MatchScore? {
            if (scoreStr.isNullOrEmpty()) return null
            // Expected format: "245/7" or "245"
            return try {
                val parts = scoreStr.split("/")
                val runs = parts[0].toIntOrNull() ?: 0
                val wickets = if (parts.size > 1) parts[1].toIntOrNull() ?: 0 else 0
                com.example.worldcupapp.domain.model.MatchScore(runs, wickets, 0f) // Overs not in simple string
            } catch (e: Exception) { null }
        }

        return Match(
            id = this.id ?: "unknown",
            teamA = com.example.worldcupapp.domain.model.Team(this.t1 ?: "Unknown", this.t1 ?: "Unknown", "", this.t1img ?: "", 0),
            teamB = com.example.worldcupapp.domain.model.Team(this.t2 ?: "Unknown", this.t2 ?: "Unknown", "", this.t2img ?: "", 0),
            scoreA = parseScore(this.t1s),
            scoreB = parseScore(this.t2s),
            status = if (this.status == "Match Ended") com.example.worldcupapp.domain.model.MatchStatus.COMPLETED else com.example.worldcupapp.domain.model.MatchStatus.LIVE,
            venue = "Live Venue",
            startTime = System.currentTimeMillis()
        )
    }
}
