package com.example.worldcupapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlayerResponseDto(
    @SerializedName("data")
    val data: List<PlayerDto>
)

data class PlayerDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String?,
    @SerializedName("image")
    val image: String?
)
