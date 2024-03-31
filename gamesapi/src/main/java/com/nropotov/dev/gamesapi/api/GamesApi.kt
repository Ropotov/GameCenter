package com.nropotov.dev.gamesapi.api

import com.nropotov.dev.gamesapi.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("games")
    suspend fun loadAllGames(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
    ): GamesResponse

    @GET("games")
    suspend fun searchGames(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("search") search: String,
    ): GamesResponse
}
