package com.nicolas.nicolsflix.home.data.repository

import com.nicolas.nicolsflix.home.domain.domain.MovieUiDomain

interface MovieRepository {

    suspend fun fetchMoviePopular(): List<MovieUiDomain>

}