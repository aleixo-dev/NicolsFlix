package com.nicolas.nicolsflix.presentation.new_detail

sealed class NewDetailEvent {

    data class OnOpened(var movieId : Int?) : NewDetailEvent()
}