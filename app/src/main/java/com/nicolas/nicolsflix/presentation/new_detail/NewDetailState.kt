package com.nicolas.nicolsflix.presentation.new_detail

import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.nicolas.nicolsflix.network.models.remote.MovieDetailsResponse

sealed class NewDetailState {

    object Loading : NewDetailState()

    data class SetupViews(
        var details: MovieDetailsResponse? = null,
        var cast: List<CastFromMovie>? = emptyList()
    ) : NewDetailState()

    object Error : NewDetailState()

}
