package com.nropotov.dev.games.domain.usecases

import com.nropotov.dev.games.domain.repository.GamesRepository
import com.nropotov.dev.games.domain.models.UiGamesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllGamesUseCase {
    suspend operator fun invoke() : List<UiGamesModel>
}

class GetAllGamesUseCaseImpl @Inject constructor(
    private val gamesRepository: GamesRepository
) : GetAllGamesUseCase {
    override suspend fun invoke() = gamesRepository.getAllGames()
}