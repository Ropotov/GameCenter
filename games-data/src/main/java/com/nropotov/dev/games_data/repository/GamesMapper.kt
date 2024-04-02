package com.nropotov.dev.games_data.repository

import com.nropotov.dev.games.domain.models.UiGamePlatform
import com.nropotov.dev.games.domain.models.UiGamesModel
import com.nropotov.dev.gamesapi.model.GamePlatform
import com.nropotov.dev.gamesapi.model.GamesModel
import javax.inject.Inject

class GamesMapper @Inject constructor() {

    fun mapGamesModelToUiGamesModel(gamesModel: GamesModel): UiGamesModel =
        UiGamesModel(
            id = gamesModel.id ?: 0,
            name = gamesModel.name ?: emptyString(),
            releaseDate = gamesModel.releaseDate ?: emptyString(),
            imageUrl = gamesModel.imageUrl ?: emptyString(),
            rating = gamesModel.rating ?: 0.0,
            ratingTop = gamesModel.ratingTop ?: 0,
            metacriticPercent = gamesModel.metacriticPercent ?: 0,
            platforms = gamesModel.platforms?.map { mapGamesModelToUiGamesModel(it) } ?: emptyList()
        )

    private fun mapGamesModelToUiGamesModel(platform: GamePlatform): UiGamePlatform =
        UiGamePlatform(
            id = platform.id ?: 0,
            name = platform.name ?: emptyString()
        )

    private fun emptyString(): String = ""
}
