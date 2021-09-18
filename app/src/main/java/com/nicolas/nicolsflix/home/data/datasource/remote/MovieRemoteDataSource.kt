package com.nicolas.nicolsflix.home.data.datasource.remote

import com.nicolas.nicolsflix.home.data.model.ResponseMovie

interface MovieRemoteDataSource {

    suspend fun fetchMoviePopular() : ResponseMovie
}