package com.nropotov.dev.games.domain.usecases

import com.nropotov.dev.games.domain.repository.GamesRepository
import com.nropotov.dev.games.domain.models.UiGamesModel
import javax.inject.Inject

interface GetAllGamesAlongPcUseCase {
    suspend operator fun invoke() : List<UiGamesModel>
}

class GetAllGamesAlongPcUseCaseImpl @Inject constructor(
    private val gamesRepository: GamesRepository
) : GetAllGamesAlongPcUseCase {
    override suspend fun invoke() = gamesRepository.getAllGamesAlongPC()
}
