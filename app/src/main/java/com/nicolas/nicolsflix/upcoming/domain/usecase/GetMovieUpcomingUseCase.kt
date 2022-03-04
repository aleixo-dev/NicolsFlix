package com.nicolas.nicolsflix.upcoming.domain.usecase

import com.nicolas.nicolsflix.common.Resource
import com.nicolas.nicolsflix.upcoming.data.repository.UpcomingRepository
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain
import com.nicolas.nicolsflix.upcoming.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetMovieUpcomingUseCase(
    private val repository: UpcomingRepository
) : UseCase<Unit, List<UpcomingUiDomain>> {

    override suspend fun execute(params: Unit?): Flow<Resource<List<UpcomingUiDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = repository.getUpcomingList()
            emit(Resource.Success(response))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.message()))
        }
    }
}