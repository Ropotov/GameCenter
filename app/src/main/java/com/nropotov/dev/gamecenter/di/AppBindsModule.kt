package com.nropotov.dev.gamecenter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nropotov.dev.games.presentation.GamesScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppBindsModule {
    @Binds
    fun bindsViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GamesScreenViewModel::class)
    fun bindGamesViewModel(viewModel: GamesScreenViewModel): ViewModel
}
