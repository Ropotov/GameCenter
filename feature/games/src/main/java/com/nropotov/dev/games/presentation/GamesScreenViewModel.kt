package com.nropotov.dev.games.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nropotov.dev.games.domain.usecases.GetAllGamesAlongPcUseCase
import com.nropotov.dev.games.domain.usecases.GetAllGamesAlongPs5UseCase
import com.nropotov.dev.games.domain.usecases.GetAllGamesUseCase
import com.nropotov.dev.games.presentation.GamesUiState.Success.Companion
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GamesScreenViewModel @Inject constructor(
    private val getAllGamesUseCase: GetAllGamesUseCase,
    private val getAllGamesAlongPcUseCase: GetAllGamesAlongPcUseCase,
    private val getAllGamesAlongPs5UseCase: GetAllGamesAlongPs5UseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<GamesUiState>(GamesUiState.Loading)
    val screenState get() = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val gamesStateBuilder = getBuilder(_screenState.value)
        viewModelScope.launch {
            listOf(
                async { getAllGames(gamesStateBuilder) },
                async { getPcGames(gamesStateBuilder) },
                async { getPs5Games(gamesStateBuilder) },
            ).awaitAll()
            _screenState.tryEmit(
                gamesStateBuilder.build()
            )
        }
    }

    private fun getBuilder(uiState: GamesUiState): Companion.Builder {
        return if (uiState is GamesUiState.Success) Companion.Builder(uiState)
        else Companion.Builder()
    }

    private suspend fun getAllGames(builder: Companion.Builder) {
        builder.saveAllGames(getAllGamesUseCase.invoke())
    }

    private suspend fun getPcGames(builder: Companion.Builder) {
        builder.savePcGames(getAllGamesAlongPcUseCase.invoke())
    }

    private suspend fun getPs5Games(builder: Companion.Builder) {
        builder.savePs5Games(getAllGamesAlongPs5UseCase.invoke())
    }
}
