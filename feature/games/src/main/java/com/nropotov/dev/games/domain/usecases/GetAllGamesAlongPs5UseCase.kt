package com.nropotov.dev.games.domain.usecases

import com.nropotov.dev.games.domain.repository.GamesRepository
import com.nropotov.dev.games.domain.models.UiGamesModel
import javax.inject.Inject

interface GetAllGamesAlongPs5UseCase {
    suspend operator fun invoke() : List<UiGamesModel>
}

class GetAllGamesAlongPs5UseCaseCaseImpl @Inject constructor(
    private val gamesRepository: GamesRepository
) : GetAllGamesAlongPs5UseCase {
    override suspend fun invoke() = gamesRepository.getAllGamesAlongPS5()
}
