package com.nropotov.dev.games.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GamesScreen(viewModelFactory: ViewModelProvider.Factory) {
    GamesScreen(
        viewModel = viewModel(factory = viewModelFactory)
    )
}

@Composable
internal fun GamesScreen(
    viewModel: GamesScreenViewModel
) {

    val games by viewModel.games.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(games) {
            Text(text = it.name)
        }
    }
}