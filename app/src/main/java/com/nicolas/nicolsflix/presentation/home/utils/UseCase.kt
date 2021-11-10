package com.nicolas.nicolsflix.presentation.home.utils

interface UseCase<in Params, out Output> {
    suspend fun execute(params: Params? = null): DataState<Output>
}