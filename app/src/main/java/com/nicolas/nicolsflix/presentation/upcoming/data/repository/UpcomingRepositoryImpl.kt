package com.nicolas.nicolsflix.presentation.upcoming.data.repository

import com.nicolas.nicolsflix.presentation.upcoming.data.datasource.remote.UpcomingRemoteDataSource
import com.nicolas.nicolsflix.presentation.upcoming.data.mapper.toUiDomain
import com.nicolas.nicolsflix.presentation.upcoming.domain.model.UpcomingUiDomain

class UpcomingRepositoryImpl(
    private val remoteDataSource: UpcomingRemoteDataSource
) : UpcomingRepository {

    override suspend fun getUpcomingList(): List<UpcomingUiDomain> {
        val response = remoteDataSource.getMovieUpcoming()
        return response?.let {
            it.toUiDomain(response.results)
        }
    }
}