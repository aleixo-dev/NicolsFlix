package com.nicolas.nicolsflix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val movieApiRepositoryImpl: MovieApiRepositoryImpl) : ViewModel() {

    val listMovieTrending = MutableLiveData<ArrayList<Movie>>()
    val listSearchMovie = MutableLiveData<ArrayList<Movie>>()

    init {
        callMovieTrending()
    }


    private fun callMovieTrending() {
        viewModelScope.launch {
            listMovieTrending.value = movieApiRepositoryImpl.getMovieTrending()
        }
    }

    fun callSearchMovie(titleMovie: String) {
        viewModelScope.launch {
            listSearchMovie.value = movieApiRepositoryImpl.getMovieSearch(titleMovie)
        }
    }

}