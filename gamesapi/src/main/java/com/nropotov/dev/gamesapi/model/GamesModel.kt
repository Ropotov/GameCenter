package com.nropotov.dev.gamesapi.model

import com.google.gson.annotations.SerializedName

data class GamesModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("released") val releaseDate: String,
    @SerializedName("background_image") val imageUrl: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("rating_top") val ratingTop: Int,
    @SerializedName("metacritic") val metacriticPercent: Int,
    @SerializedName("platforms") val platforms: List<GamePlatform>,
)
