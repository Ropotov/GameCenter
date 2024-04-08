package com.nropotov.dev.gamesdata.repository

import com.nropotov.dev.games.domain.models.UiGamesModel
import com.nropotov.dev.games.domain.repository.GamesRepository
import com.nropotov.dev.gamesapi.api.GamesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val api: GamesApi,
    private val mapper: GamesMapper
) : GamesRepository {
    override suspend fun getAllGames(): List<UiGamesModel> {
        return withContext(Dispatchers.IO) {
            api.loadAllGames().gamesList?.map {
                mapper.mapGamesModelToUiGamesModel(it)
            }?.sortedBy { it.releaseDate }?.reversed()
        } ?: emptyList()
    }

    override suspend fun getAllGamesAlongPC(): List<UiGamesModel> {
        return withContext(Dispatchers.IO) {
            api.loadAllGamesAlongPC().gamesList?.map {
                mapper.mapGamesModelToUiGamesModel(it)
            }?.sortedBy { it.releaseDate }?.reversed()
        } ?: emptyList()
    }

    override suspend fun getAllGamesAlongPS5(): List<UiGamesModel> {
        return withContext(Dispatchers.IO) {
            api.loadAllGamesAlongPS5().gamesList?.map {
                mapper.mapGamesModelToUiGamesModel(it)
            }?.sortedBy { it.releaseDate }?.reversed()
        } ?: emptyList()
    }

    override suspend fun getSearchGames(query: String): List<UiGamesModel> {
        return withContext(Dispatchers.IO) {
            api.searchGames(search = query).gamesList?.map {
                mapper.mapGamesModelToUiGamesModel(it)
            }?.sortedBy { it.releaseDate }?.reversed()
        } ?: emptyList()
    }
}
