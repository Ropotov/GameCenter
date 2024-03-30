package com.nropotov.dev.gamesapi.model

import com.google.gson.annotations.SerializedName

data class GamePlatform(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
