package com.nicolas.nicolsflix.presentation.upcoming.data.datasource.remote

import com.nicolas.nicolsflix.presentation.upcoming.data.model.UpcomingResponse

interface UpcomingRemoteDataSource {

    suspend fun getMovieUpcoming() : UpcomingResponse

}