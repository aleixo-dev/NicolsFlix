package com.nicolas.nicolsflix.upcoming.data.mapper

import com.nicolas.nicolsflix.upcoming.data.model.Result
import com.nicolas.nicolsflix.upcoming.data.model.UpcomingResponse
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain

fun UpcomingResponse.toUiDomain(listUpcoming: List<Result>): ArrayList<UpcomingUiDomain> {

    val newListDomain = ArrayList<UpcomingUiDomain>()

    for (movie in listUpcoming) {
        val dataMovie = UpcomingUiDomain(
            id = movie.id.toString(),
            title = movie.title,
            imagePoster = movie.posterPath,
            description = movie.overview,
            releaseDate = movie.releaseDate,
            vote = movie.voteAverage.toString()
        )
        newListDomain.add(dataMovie)
    }
    return newListDomain
}