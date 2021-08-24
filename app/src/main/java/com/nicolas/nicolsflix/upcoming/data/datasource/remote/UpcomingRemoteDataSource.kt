package com.nicolas.nicolsflix.upcoming.data.datasource.remote

import com.nicolas.nicolsflix.upcoming.data.model.UpcomingResponse

interface UpcomingRemoteDataSource {

    suspend fun getMovieUpcoming() : UpcomingResponse

}