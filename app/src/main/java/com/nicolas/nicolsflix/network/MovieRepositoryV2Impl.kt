package com.nicolas.nicolsflix.network

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.mapper.MovieMapper
import com.nicolas.nicolsflix.network.models.remote.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow
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

    override suspend fun getTrendingMovie(): Flow<List<Movie>> = flow {
        val response = remote.getTrendingMovie()
        emit(MovieMapper.responseToDomain(response.results))
    }

    override suspend fun getDetailsMovie(movieId: Int): Flow<MovieDetailsResponse> = flow {
        val response = remote.getMovieDetails(movieId)
        emit(response)
    }
}