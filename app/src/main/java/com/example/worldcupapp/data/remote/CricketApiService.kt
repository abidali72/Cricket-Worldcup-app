package com.example.worldcupapp.data.remote

import com.example.worldcupapp.data.remote.dto.PlayerResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CricketApiService {
    
    @GET("cricket-players")
    suspend fun getPlayers(@Query("teamid") teamId: Int): PlayerResponseDto

    @GET("cricket-livescores")
    suspend fun getLiveScores(): com.example.worldcupapp.data.remote.dto.LiveScoreResponseDto
}
