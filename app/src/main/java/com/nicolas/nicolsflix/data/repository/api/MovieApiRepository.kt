package com.nicolas.nicolsflix.data.repository.api

import com.nicolas.nicolsflix.data.model.Movie

interface MovieApiRepository {

    suspend fun getMovieTrending(): ArrayList<Movie>?
    suspend fun getMoviePopular(): ArrayList<Movie>?
    suspend fun getMovieRecommend(): ArrayList<Movie>?
    suspend fun getMovieSimilar(movieId : Int): ArrayList<Movie>?
    suspend fun getMovieSearch(movieName : String?): ArrayList<Movie>?

}