package com.nicolas.nicolsflix.home.data.datasource.remote

import com.nicolas.nicolsflix.home.data.model.ResponseMovie
import com.nicolas.nicolsflix.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(Constants.ENDPOINT_POPULAR)
    suspend fun fetchMoviePopular(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : ResponseMovie
}