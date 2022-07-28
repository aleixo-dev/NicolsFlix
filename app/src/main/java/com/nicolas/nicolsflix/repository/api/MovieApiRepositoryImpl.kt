package com.nicolas.nicolsflix.repository.api

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.model.MovieResponse
import com.nicolas.nicolsflix.data.network.RetrofitInitializer
import com.nicolas.nicolsflix.data.network.mapper.MovieMapper
import com.nicolas.nicolsflix.data.network.mapper.MovieSearchMapper
import com.nicolas.nicolsflix.data.network.mapper.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.awaitResponse

class MovieApiRepositoryImpl(
    private val movieApi: RetrofitInitializer,
    private val coroutineDispatcher: CoroutineDispatcher
) : MovieApiRepository {

    override suspend fun getMovieTrending(): Flow<List<Movie>> = flow {
        val service = movieApi.movieTrendingService().getMovieTrending()
        val body = service.results
        emit(MovieMapper.responseToDomain(body))
    }

    override suspend fun getMovieSimilar(movieId: Int): ArrayList<Movie>? {

        val service = movieApi.movieSimilarService().getMovieSimiliar(movieId).awaitResponse()
        val body = service.body()
        return if (service.isSuccessful && body != null) {
            MovieMapper.responseToDomain(body.results)
        } else {
            null
        }
    }

    override suspend fun getMovieSearch(movieName: String?): Flow<List<Movie>> = flow {
        val response = movieApi.movieSearchService().getSearchMovie(movieName)
        response.toDomain().searchMovies?.let { emit(it) }
    }.flowOn(coroutineDispatcher)

    override suspend fun getPopularMovie(): ArrayList<Movie>? {
        // TODO: refatorar!
        val service = movieApi.getPopularMovie().getMoviePopular().awaitResponse()
        val body = service.body()
        return if (service.isSuccessful && body != null) {
            MovieMapper.responseToDomain(body.results)
        } else {
            null
        }
    }
}