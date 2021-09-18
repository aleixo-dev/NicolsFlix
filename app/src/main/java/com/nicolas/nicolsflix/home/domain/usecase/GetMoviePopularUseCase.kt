package com.nicolas.nicolsflix.home.domain.usecase

import com.nicolas.nicolsflix.home.data.repository.MovieRepository
import com.nicolas.nicolsflix.home.domain.domain.MovieUiDomain
import com.nicolas.nicolsflix.home.utils.DataState
import com.nicolas.nicolsflix.home.utils.UseCase

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