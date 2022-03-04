package com.nicolas.nicolsflix.common

sealed class ViewState<T> {
    data class Success<T>(val data: T) : ViewState<T>()
    class Loading<T> : ViewState<T>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }
    class Empty<T> : ViewState<T>()
    class InvalidParam<T>(val messagesResource: List<Int?>) : ViewState<T>()
    data class Error<T>(val errorType: String) : ViewState<T>()
}
