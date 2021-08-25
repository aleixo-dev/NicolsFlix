package com.nicolas.nicolsflix.upcoming.domain.usecase

import com.nicolas.nicolsflix.upcoming.data.repository.UpcomingRepository
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain
import com.nicolas.nicolsflix.upcoming.utils.DataState
import com.nicolas.nicolsflix.upcoming.utils.UseCase

class GetMovieUpcomingUseCase(
    private val repository: UpcomingRepository
) : UseCase<Unit, List<UpcomingUiDomain>> {

    override suspend fun execute(params: Unit?): DataState<List<UpcomingUiDomain>> {
        val response = repository.getUpcomingList()

        return if (response.isEmpty().not()) {
            DataState.Success(response)
        } else {
            DataState.Empty
        }
    }
}