package com.nropotov.dev.games.domain.models

import androidx.compose.runtime.Immutable

@Immutable
data class UiGamesModel(
    val id: Int,
    val name: String,
    val releaseDate: String,
    val imageUrl: String,
    val rating: Double,
    val ratingTop: Int,
    val metacriticPercent: Int,
    val platforms: List<UiGamePlatform>,
)
