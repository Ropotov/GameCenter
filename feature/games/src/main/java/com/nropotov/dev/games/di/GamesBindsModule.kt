package com.nropotov.dev.games.di

import com.nropotov.dev.games.domain.repository.GamesRepository
import com.nropotov.dev.games.domain.usecases.GetAllGamesUseCase
import com.nropotov.dev.games.domain.usecases.GetAllGamesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface GamesBindsModule {
    @Binds
    fun bindsGetAllGamesUseCase(impl: GetAllGamesUseCaseImpl) : GetAllGamesUseCase

}
