package com.example.worldcupapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LiveScoreResponseDto(
    @SerializedName("data")
    val data: List<LiveScoreDto>?
)

data class LiveScoreDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("series")
    val series: String?,
    @SerializedName("t1")
    val t1: String?, // Team 1 Name
    @SerializedName("t2")
    val t2: String?, // Team 2 Name
    @SerializedName("t1s")
    val t1s: String?, // Team 1 Score (e.g. "245/7")
    @SerializedName("t2s")
    val t2s: String?, // Team 2 Score
    @SerializedName("status")
    val status: String?, // e.g. "Match Ended", "Live"
    @SerializedName("t1img")
    val t1img: String?,
    @SerializedName("t2img")
    val t2img: String?
)
