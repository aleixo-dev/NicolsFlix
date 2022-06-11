package com.nicolas.nicolsflix.presentation.home.presentation

import com.nicolas.nicolsflix.data.model.Movie

sealed class HomeState {

    object Loading : HomeState()

    data class Error(
        val errorMessage: String? = null
    ) : HomeState()

    data class Success(
        val movies: List<Movie>
    ) : HomeState()
}
