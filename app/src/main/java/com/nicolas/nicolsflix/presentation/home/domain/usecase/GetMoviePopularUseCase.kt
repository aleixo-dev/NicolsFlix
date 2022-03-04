package com.nicolas.nicolsflix.presentation.home.domain.usecase

import com.nicolas.nicolsflix.presentation.home.data.repository.MovieRepository
import com.nicolas.nicolsflix.presentation.home.domain.domain.MovieUiDomain
import com.nicolas.nicolsflix.presentation.home.utils.UseCase
import com.nicolas.nicolsflix.upcoming.utils.DataState

class GetMoviePopularUseCase(
    private val repository: MovieRepository
) : UseCase<Unit, List<MovieUiDomain>> {
    override suspend fun execute(params: Unit?): DataState<List<MovieUiDomain>> {

        val response = repository.fetchMoviePopular()
        return if (response.isNotEmpty()) {
            DataState.Success(response)
        } else {
            DataState.Empty
        }
    }
}