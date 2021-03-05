package com.nicolas.nicolsflix.data.repository.database

import com.nicolas.nicolsflix.data.model.Movie

interface MovieRepository {

    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    suspend fun getAllMovie(): List<Movie>

}