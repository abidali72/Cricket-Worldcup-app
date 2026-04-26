package com.example.worldcupapp.data.remote

import com.example.worldcupapp.domain.model.Match
import com.example.worldcupapp.domain.model.MatchScore
import com.example.worldcupapp.domain.model.MatchStatus
import com.example.worldcupapp.domain.model.Team
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockLiveDataSource @Inject constructor() {

    private val india = Team("IND", "India", "IND", "https://flagcdn.com/w320/in.png", 1)
    private val australia = Team("AUS", "Australia", "AUS", "https://flagcdn.com/w320/au.png", 2)
    private val england = Team("ENG", "England", "ENG", "https://flagcdn.com/w320/gb-eng.png", 3)
    private val pakistan = Team("PAK", "Pakistan", "PAK", "https://flagcdn.com/w320/pk.png", 4)

    private var currentMatch = Match(
        id = "m1",
        teamA = india,
        teamB = australia,
        scoreA = MatchScore(245, 7, 50.0f),
        scoreB = MatchScore(112, 2, 18.3f, 246),
        status = MatchStatus.LIVE,
        venue = "Narendra Modi Stadium, Ahmedabad",
        startTime = System.currentTimeMillis() - 3600000
    )

    fun getLiveMatchStream(): Flow<List<Match>> = flow {
        while (true) {
            // Simulate live score updates
            val currentScoreB = currentMatch.scoreB!!
            val newRuns = currentScoreB.runs + (0..6).random() // Random runs per ball/update
            val newOvers = (currentScoreB.overs * 10).toInt() + 1
            val updatedOvers = if (newOvers % 10 == 6) (newOvers / 10 + 1).toFloat() else newOvers / 10f
            
            // Random wicket fall (1 in 30 chance)
            val newWickets = if ((0..30).random() == 0) currentScoreB.wickets + 1 else currentScoreB.wickets

            val updatedScoreB = currentScoreB.copy(
                runs = newRuns,
                wickets = newWickets,
                overs = updatedOvers as Float
            )

            currentMatch = currentMatch.copy(scoreB = updatedScoreB)
            
            emit(listOf(currentMatch, getCompletedMatch(), getUpcomingMatch()))
            delay(2000) // Update every 2 seconds
        }
    }
    
    fun getAllMatches(): List<Match> {
         return listOf(currentMatch, getCompletedMatch(), getUpcomingMatch())
    }

    private fun getCompletedMatch() = Match(
        id = "m2",
        teamA = england,
        teamB = pakistan,
        scoreA = MatchScore(280, 8, 50.0f),
        scoreB = MatchScore(230, 10, 44.2f, 281),
        status = MatchStatus.COMPLETED,
        venue = "Eden Gardens, Kolkata",
        startTime = System.currentTimeMillis() - 86400000,
        winningSummary = "England won by 50 runs"
    )

    private fun getUpcomingMatch() = Match(
        id = "m3",
        teamA = india,
        teamB = pakistan,
        status = MatchStatus.UPCOMING,
        venue = "Old Trafford, Manchester",
        startTime = System.currentTimeMillis() + 86400000
    )
}
