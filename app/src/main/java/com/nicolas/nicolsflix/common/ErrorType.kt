package com.nicolas.nicolsflix.common

sealed class ErrorType {
    object NoConnectionError : ErrorType()
    object Timeout : ErrorType()
    object NotFound : ErrorType()
    object Unauthorized : ErrorType()
    object Unknown : ErrorType()
}