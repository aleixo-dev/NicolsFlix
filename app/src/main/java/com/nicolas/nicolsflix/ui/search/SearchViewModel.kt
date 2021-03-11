package com.nicolas.nicolsflix.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.repository.api.MovieRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel(private val repositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val listSearch = MutableLiveData<ArrayList<Movie>>()

    fun search(titleMovie: String) {
        viewModelScope.launch {
            listSearch.value = repositoryImpl.getMovieSearch(titleMovie)
        }
    }

}