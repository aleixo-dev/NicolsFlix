package com.nicolas.nicolsflix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel(private val apiRepositoryImpl: MovieApiRepositoryImpl) : ViewModel() {

    val listSearch = MutableLiveData<ArrayList<Movie>>()

    fun search(titleMovie: String) {
        viewModelScope.launch {
            listSearch.value = apiRepositoryImpl.getMovieSearch(titleMovie)
        }
    }

}