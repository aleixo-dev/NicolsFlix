package com.nicolas.nicolsflix.upcoming.utils

sealed class DataState<out T> {
    class Success<out T>(val result: T) : DataState<T>()
    object Empty : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    class Error(
        val errorMessage: Int? = null,
        val throwable: Throwable? = null
    ) : DataState<Nothing>()
}