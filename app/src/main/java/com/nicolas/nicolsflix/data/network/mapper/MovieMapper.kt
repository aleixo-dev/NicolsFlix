package com.nicolas.nicolsflix.data.network.mapper

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.api.popular.response.Result

class MovieMapper {

    companion object {
        fun responseToDomain(listResponseBody: List<Result>): ArrayList<Movie> {

            val listMoviePopular = ArrayList<Movie>()
            for (popularMovie in listResponseBody) {
                val moviePopular = Movie(
                    popularMovie.id,
                    popularMovie.title,
                    popularMovie.posterPath,
                    popularMovie.backdropPath,
                    popularMovie.overview,
                    popularMovie.voteAverage.toString(),
                    popularMovie.releaseDate
                )
                listMoviePopular.add(moviePopular)
            }
            return listMoviePopular
        }
    }
}