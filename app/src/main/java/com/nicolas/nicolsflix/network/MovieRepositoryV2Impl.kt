package com.nicolas.nicolsflix.network

import kotlinx.coroutines.flow.flow

class MovieRepositoryV2Impl(
    private val remote: MovieService
) : MovieRepositoryV2 {

    override suspend fun getCasts(movieId: Int) = flow {
        val response = remote.getCastsMovieDetails(movieId)
        emit(response.castFromMovie)
    }

    override suspend fun getTrailerMovies(movieId: Int) = flow {
        val response = remote.getTrailerVideo(movieId)
        emit(response.trailers)
    }

    override suspend fun getPersonDetail(personId: Int) = flow {
        val response = remote.getPersonDetail(personId)
        emit(response)
    }
}