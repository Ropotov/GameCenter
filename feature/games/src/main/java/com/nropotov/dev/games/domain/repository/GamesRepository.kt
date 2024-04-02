package com.nropotov.dev.games.domain.repository

import com.nropotov.dev.games.domain.models.UiGamesModel
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun getAllGames(): List<UiGamesModel>

    suspend fun getSearchGames(query: String): List<UiGamesModel>
}