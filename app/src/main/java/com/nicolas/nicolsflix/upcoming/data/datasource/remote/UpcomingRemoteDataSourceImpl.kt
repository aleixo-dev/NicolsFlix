package com.nicolas.nicolsflix.upcoming.data.datasource.remote

class UpcomingRemoteDataSourceImpl(
    private val api: UpcomingApi
) : UpcomingRemoteDataSource {

    override suspend fun getMovieUpcoming() = api.getMovieUpcoming()
}