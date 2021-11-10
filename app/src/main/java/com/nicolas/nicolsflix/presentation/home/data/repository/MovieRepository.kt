package com.nicolas.nicolsflix.presentation.home.data.repository

import com.nicolas.nicolsflix.presentation.home.domain.domain.MovieUiDomain

interface MovieRepository {

    suspend fun fetchMoviePopular(): List<MovieUiDomain>

}