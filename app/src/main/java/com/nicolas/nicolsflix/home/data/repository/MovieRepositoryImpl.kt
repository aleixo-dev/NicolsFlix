package com.nicolas.nicolsflix.home.data.repository

import com.nicolas.nicolsflix.home.data.datasource.remote.MovieRemoteDataSource
import com.nicolas.nicolsflix.home.data.mapper.toDomainUiModel
import com.nicolas.nicolsflix.home.domain.domain.MovieUiDomain
import java.lang.Exception

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