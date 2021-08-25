package com.nicolas.nicolsflix.upcoming.data.datasource.remote

import com.nicolas.nicolsflix.upcoming.data.model.UpcomingResponse

class UpcomingRemoteDataSourceImpl(
    private val api: UpcomingApi
) : UpcomingRemoteDataSource {

    override suspend fun getMovieUpcoming() = api.getMovieUpcoming()
}