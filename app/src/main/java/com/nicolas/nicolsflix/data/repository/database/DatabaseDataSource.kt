package com.nicolas.nicolsflix.data.repository.database

import com.nicolas.nicolsflix.data.db.dao.MovieDao
import com.nicolas.nicolsflix.data.db.entity.toMovies
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.model.toEntity

class DatabaseDataSource(private val movieDao: MovieDao) : MovieRepository {
    override suspend fun insertMovie(movie: Movie) {
        movieDao.insert(toEntity(movie))
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(toEntity(movie))
    }

    override suspend fun getAllMovie(): List<Movie> {
        return toMovies(movieDao.getAll())
    }
}