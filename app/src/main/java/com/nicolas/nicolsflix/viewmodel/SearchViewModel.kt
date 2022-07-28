package com.nicolas.nicolsflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(private val apiRepositoryImpl: MovieApiRepositoryImpl) : ViewModel() {

    private val _listSearch = MutableLiveData<List<Movie>>()
    val listSearch: LiveData<List<Movie>> get() = _listSearch

    fun search(titleMovie: String) {
        viewModelScope.launch {
            apiRepositoryImpl.getMovieSearch(titleMovie)
                .onStart { }
                .catch { }
                .collect {
                    _listSearch.value
                }
        }
    }
}