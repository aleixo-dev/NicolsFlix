package com.nicolas.nicolsflix.presentation.home.data.datasource.remote

import com.nicolas.nicolsflix.presentation.home.data.model.ResponseMovie
import com.nicolas.nicolsflix.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(Constants.ENDPOINT_POPULAR)
    suspend fun fetchMoviePopular(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : ResponseMovie
}