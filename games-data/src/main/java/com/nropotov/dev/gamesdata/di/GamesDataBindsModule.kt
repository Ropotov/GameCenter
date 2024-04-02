package com.nropotov.dev.gamesdata.di

import com.nropotov.dev.games.domain.repository.GamesRepository
import com.nropotov.dev.gamesdata.repository.GamesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface GamesDataBindsModule {
    @Binds
    fun bindsGamesRepository(impl: GamesRepositoryImpl): GamesRepository
}
