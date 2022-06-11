package com.nicolas.nicolsflix.repository.api

import com.nicolas.nicolsflix.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieApiRepository {

    suspend fun getMovieTrending(): Flow<List<Movie>>
    suspend fun getMovieSimilar(movieId: Int): ArrayList<Movie>?
    suspend fun getMovieSearch(movieName: String?): ArrayList<Movie>?

    /** New Implementation 'MovieService' */
    suspend fun getPopularMovie(): ArrayList<Movie>?

}