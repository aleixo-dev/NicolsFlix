package com.nicolas.nicolsflix.home.data.datasource.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSourceImpl(
    private val api: MovieApi
) : MovieRemoteDataSource {

    override suspend fun fetchMoviePopular() = withContext(Dispatchers.IO) {
        api.fetchMoviePopular()
    }
}