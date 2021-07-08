package com.nicolas.nicolsflix.repository.api

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.RetrofitInitializer
import com.nicolas.nicolsflix.data.network.mapper.MovieMapper
import com.nicolas.nicolsflix.data.network.mapper.MovieSearchMapper
import retrofit2.awaitResponse

class MovieApiRepositoryImpl(private val movieApi: RetrofitInitializer) : MovieApiRepository {

    override suspend fun getMovieTrending(): ArrayList<Movie>? {

        val service = movieApi.movieTrendingService().getMovieTrending().awaitResponse()
        val body = service.body()
        return if (service.isSuccessful && body != null) {
            MovieMapper.responseToDomain(body.results)
        } else {
            null
        }
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

    override suspend fun getMovieSearch(movieName: String?): ArrayList<Movie>? {

        val service = movieApi.movieSearchService().getSearchMovie(movieName).awaitResponse()
        val body = service.body()
        return if (service.isSuccessful && body != null) {
            MovieSearchMapper.responseToDomain(body.results)
        } else {
            null
        }
    }
}