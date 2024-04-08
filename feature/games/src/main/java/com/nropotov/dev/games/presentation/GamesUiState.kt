package com.nropotov.dev.games.presentation

import com.nropotov.dev.games.domain.models.UiGamesModel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

sealed interface GamesUiState {

    data object Loading : GamesUiState

    data object Error : GamesUiState

    data class Success(
        val allGames: List<UiGamesModel>,
        val pcGames: List<UiGamesModel>,
        val ps5Games: List<UiGamesModel>
    ) : GamesUiState {

        companion object {

            class Builder private constructor(
                private var allGames: List<UiGamesModel> = listOf(),
                private var pcGames: List<UiGamesModel> = listOf(),
                private var ps5Games: List<UiGamesModel> = listOf()
            ) {
                constructor(success: Success) : this(
                    allGames = success.allGames,
                    pcGames = success.pcGames,
                    ps5Games = success.ps5Games
                )

                constructor() : this(emptyList(), emptyList(), emptyList())

                private val mutex = Mutex()

                suspend fun saveAllGames(allGames: List<UiGamesModel>) = mutex.withLock {
                    apply { this.allGames = allGames }
                }

                suspend fun savePcGames(pcGames: List<UiGamesModel>) = mutex.withLock {
                    apply { this.pcGames = pcGames }
                }

                suspend fun savePs5Games(ps5Games: List<UiGamesModel>) = mutex.withLock {
                    apply { this.ps5Games = ps5Games }
                }

                fun build(): Success = Success(
                    allGames = this.allGames,
                    pcGames = this.pcGames,
                    ps5Games = this.ps5Games
                )
            }
        }
    }
}
