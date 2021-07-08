package com.nicolas.nicolsflix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import com.nicolas.nicolsflix.repository.database.MovieDaoRepositoryImpl
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val movieDaoRepositoryImpl: MovieDaoRepositoryImpl,
    private val movieApiRepositoryImpl: MovieApiRepositoryImpl
) : ViewModel() {

    val isMovieMyList = MutableLiveData<Boolean>()
    val listMovieSimilar = MutableLiveData<List<Movie>>()

    fun insertMovieMyList(movie: Movie) {
        viewModelScope.launch {
            movieDaoRepositoryImpl.insertMovie(movie)
        }
    }

    fun deleteMovieMyList(movie: Movie) {
        viewModelScope.launch {
            movieDaoRepositoryImpl.deleteMovie(movie)
        }
    }

    fun isMovieMyList(movie: Movie) {
        viewModelScope.launch {
            isMovieMyList.value = movieDaoRepositoryImpl.getAllMovie().contains(movie)
        }
    }

    fun getMovieSimilar(movieId: Int) {

        viewModelScope.launch {
            listMovieSimilar.value = movieApiRepositoryImpl.getMovieSimilar(movieId)
        }
    }
}