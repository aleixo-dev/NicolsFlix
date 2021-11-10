package com.nicolas.nicolsflix.presentation.home.data.repository

import com.nicolas.nicolsflix.presentation.home.data.datasource.remote.MovieRemoteDataSource
import com.nicolas.nicolsflix.presentation.home.data.mapper.toDomainUiModel
import com.nicolas.nicolsflix.presentation.home.domain.domain.MovieUiDomain

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun fetchMoviePopular(): List<MovieUiDomain> {

        val response = remoteDataSource.fetchMoviePopular()
        return if(response.results.isNotEmpty()){
            response.toDomainUiModel(response.results)
        }else{
            emptyList()
        }
    }
}