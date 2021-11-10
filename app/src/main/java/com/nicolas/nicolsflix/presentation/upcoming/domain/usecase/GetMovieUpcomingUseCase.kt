package com.nicolas.nicolsflix.presentation.upcoming.domain.usecase

import com.nicolas.nicolsflix.presentation.upcoming.data.repository.UpcomingRepository
import com.nicolas.nicolsflix.presentation.upcoming.domain.model.UpcomingUiDomain
import com.nicolas.nicolsflix.presentation.upcoming.utils.DataState
import com.nicolas.nicolsflix.presentation.upcoming.utils.UseCase

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