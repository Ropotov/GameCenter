package com.nropotov.dev.games.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nropotov.dev.games.domain.models.UiGamesModel
import com.nropotov.dev.games.domain.usecases.GetAllGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GamesScreenViewModel @Inject constructor(
    private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {

    private val _games = MutableStateFlow<List<UiGamesModel>>(emptyList())
    val games get() = _games.asStateFlow()

    init {
        getAllGames()
    }
    private fun getAllGames() {
        viewModelScope.launch {
            _games.tryEmit(getAllGamesUseCase.invoke())
        }
    }
}