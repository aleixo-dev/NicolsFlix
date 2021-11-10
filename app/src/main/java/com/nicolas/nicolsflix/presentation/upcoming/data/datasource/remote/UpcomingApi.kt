package com.nicolas.nicolsflix.presentation.upcoming.data.datasource.remote

import com.nicolas.nicolsflix.presentation.upcoming.data.model.UpcomingResponse
import com.nicolas.nicolsflix.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingApi {

    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : UpcomingResponse
}