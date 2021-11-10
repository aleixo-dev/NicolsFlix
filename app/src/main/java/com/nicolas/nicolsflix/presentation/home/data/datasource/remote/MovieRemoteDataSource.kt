package com.nicolas.nicolsflix.presentation.home.data.datasource.remote

import com.nicolas.nicolsflix.presentation.home.data.model.ResponseMovie

interface MovieRemoteDataSource {

    suspend fun fetchMoviePopular() : ResponseMovie
}