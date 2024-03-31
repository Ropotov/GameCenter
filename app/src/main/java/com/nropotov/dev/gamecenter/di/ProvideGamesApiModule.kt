package com.nropotov.dev.gamecenter.di

import com.nropotov.dev.gamecenter.BuildConfig
import com.nropotov.dev.gamesapi.api.ApiService
import com.nropotov.dev.gamesapi.api.GamesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProvideGamesApiModule {

    @Provides
    @Singleton
    fun provideGamesApi(): GamesApi = ApiService.getGamesApi(
        apiKey = BuildConfig.API_KEY,
        baseUrl = BuildConfig.BASE_URL,
    )
}
