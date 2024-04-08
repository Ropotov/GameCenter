package com.nropotov.dev.gamesapi.api

import com.nropotov.dev.gamesapi.model.GamesResponse
import com.nropotov.dev.gamesapi.model.Platform
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("games")
    suspend fun loadAllGames(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
    ): GamesResponse

    @GET("games")
    suspend fun loadAllGamesAlongPC(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("platform") platform: Int = Platform.PC.id
    ): GamesResponse

    @GET("games")
    suspend fun loadAllGamesAlongPS5(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("platform") platform: Int = Platform.PS5.id
    ): GamesResponse

    @GET("games")
    suspend fun searchGames(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("search") search: String,
    ): GamesResponse
}
