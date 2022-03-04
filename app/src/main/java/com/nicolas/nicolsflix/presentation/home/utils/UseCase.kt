package com.nicolas.nicolsflix.presentation.home.utils

import com.nicolas.nicolsflix.upcoming.utils.DataState

interface UseCase<in Params, out Output> {
    suspend fun execute(params: Params? = null): DataState<Output>
}