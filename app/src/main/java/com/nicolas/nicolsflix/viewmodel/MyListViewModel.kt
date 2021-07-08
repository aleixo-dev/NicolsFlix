package com.nicolas.nicolsflix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.repository.database.MovieDaoRepositoryImpl
import kotlinx.coroutines.launch

class MyListViewModel(private val movieDaoRepositoryImpl: MovieDaoRepositoryImpl) : ViewModel() {

    val myListMovie = MutableLiveData<List<Movie>>()

    init {
        getAllMyListMovie()
    }

    private fun getAllMyListMovie() {
        viewModelScope.launch {
            myListMovie.value = movieDaoRepositoryImpl.getAllMovie()
        }
    }
}