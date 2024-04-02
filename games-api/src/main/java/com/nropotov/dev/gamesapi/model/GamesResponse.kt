package com.nropotov.dev.gamesapi.model

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val nextPage: String?,
    @SerializedName("previous") val prevPage: String?,
    @SerializedName("results") val gamesList: List<GamesModel>?
)
