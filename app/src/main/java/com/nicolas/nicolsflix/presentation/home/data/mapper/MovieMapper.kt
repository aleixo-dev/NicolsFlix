package com.nicolas.nicolsflix.presentation.home.data.mapper

import com.nicolas.nicolsflix.presentation.home.data.model.ResponseMovie
import com.nicolas.nicolsflix.presentation.home.data.model.ResponseResultMovie
import com.nicolas.nicolsflix.presentation.home.domain.domain.MovieUiDomain

fun ResponseMovie.toDomainUiModel(movieList: List<ResponseResultMovie>): List<MovieUiDomain> {

    val newListMovieUiDomain = ArrayList<MovieUiDomain>()
    for (movie in movieList) {
        val domainMovie = MovieUiDomain(
            id = movie.id.toString(),
            title = movie.title,
            description = movie.overview,
            imagePoster = movie.posterPath,
            imageBackground = movie.backdropPath,
            rating = movie.voteAverage.toString(),
            date = movie.releaseDate
        )
        newListMovieUiDomain.add(domainMovie)
    }
    return newListMovieUiDomain
}