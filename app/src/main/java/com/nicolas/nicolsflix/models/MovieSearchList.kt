package com.nicolas.nicolsflix.models

import com.nicolas.nicolsflix.data.model.Movie

data class MovieSearchList(
    var searchMovies: List<Movie>? = emptyList()
)
