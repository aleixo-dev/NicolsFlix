package com.nicolas.nicolsflix.upcoming.utils

interface UseCase<in Params, out Output> {
    suspend fun execute(params: Params? = null): DataState<Output>
}