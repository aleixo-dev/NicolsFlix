package com.nicolas.nicolsflix.upcoming.data.datasource.remote

import com.nicolas.nicolsflix.upcoming.data.model.UpcomingResponse
import com.nicolas.nicolsflix.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingApi {

    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : UpcomingResponse
}