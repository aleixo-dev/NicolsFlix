package com.nicolas.nicolsflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieApiRepositoryImpl: MovieApiRepositoryImpl
) : ViewModel() {

    private val _listTrendingMovie = MutableLiveData<ArrayList<Movie>>()
    val listTrendingMovie: LiveData<ArrayList<Movie>> = _listTrendingMovie

    private val _listNamesMovie = MutableLiveData<ArrayList<Movie>>()
    val listNamesMovie: LiveData<ArrayList<Movie>> = _listNamesMovie

    private val _listPopularMovie = MutableLiveData<ArrayList<Movie>>()
    val listPopularMovie: LiveData<ArrayList<Movie>> = _listPopularMovie

    init {
        fetchTrendingMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            _listTrendingMovie.value = movieApiRepositoryImpl.getMovieTrending()
        }
    }

    fun fetchPopularMovie() {
        viewModelScope.launch {
            _listPopularMovie.value = movieApiRepositoryImpl.getPopularMovie()
        }
    }

    fun fetchNameMovie(titleMovie: String) {
        viewModelScope.launch {
            val result = movieApiRepositoryImpl.getMovieSearch(titleMovie)
            if (result.isNullOrEmpty()) {
                _listNamesMovie.value = ArrayList()
            } else {
                _listNamesMovie.value = result!!
            }
        }
    }
}