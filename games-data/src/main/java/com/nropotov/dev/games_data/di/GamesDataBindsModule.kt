package com.nropotov.dev.games_data.di

import com.nropotov.dev.games.domain.repository.GamesRepository
import com.nropotov.dev.games_data.repository.GamesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface GamesDataBindsModule {

    @Binds
    fun bindsGamesRepository(impl: GamesRepositoryImpl): GamesRepository
}