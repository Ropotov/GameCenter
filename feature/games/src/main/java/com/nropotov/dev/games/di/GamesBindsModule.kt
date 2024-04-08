package com.nropotov.dev.games.di

import com.nropotov.dev.games.domain.usecases.GetAllGamesAlongPcUseCase
import com.nropotov.dev.games.domain.usecases.GetAllGamesAlongPcUseCaseImpl
import com.nropotov.dev.games.domain.usecases.GetAllGamesAlongPs5UseCase
import com.nropotov.dev.games.domain.usecases.GetAllGamesAlongPs5UseCaseCaseImpl
import com.nropotov.dev.games.domain.usecases.GetAllGamesUseCase
import com.nropotov.dev.games.domain.usecases.GetAllGamesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface GamesBindsModule {
    @Binds
    fun bindsGetAllGamesUseCase(impl: GetAllGamesUseCaseImpl): GetAllGamesUseCase

    @Binds
    fun bindsGetPcGamesUseCase(impl: GetAllGamesAlongPcUseCaseImpl): GetAllGamesAlongPcUseCase

    @Binds
    fun bindsGetPs5GamesUseCase(impl: GetAllGamesAlongPs5UseCaseCaseImpl): GetAllGamesAlongPs5UseCase

}
