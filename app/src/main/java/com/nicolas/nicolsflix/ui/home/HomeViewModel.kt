package com.nicolas.nicolsflix.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.repository.api.MovieRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val listMovieTrending = MutableLiveData<ArrayList<Movie>>()
    val listSearchMovie = MutableLiveData<ArrayList<Movie>>()

    init {
        callMovieTrending()
    }


    private fun callMovieTrending() {
        viewModelScope.launch {
            listMovieTrending.value = movieRepositoryImpl.getMovieTrending()
        }
    }

    fun callSearchMovie(titleMovie: String) {
        viewModelScope.launch {
            listSearchMovie.value = movieRepositoryImpl.getMovieSearch(titleMovie)
        }
    }

}