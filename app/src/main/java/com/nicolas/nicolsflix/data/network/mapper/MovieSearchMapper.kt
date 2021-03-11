package com.nicolas.nicolsflix.data.network.mapper

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.api.search.response.MovieSearchResponseBody

class MovieSearchMapper {
    companion object {
        fun responseToDomain(listResponseBody: List<MovieSearchResponseBody>): ArrayList<Movie> {

            val listMovieSearch = ArrayList<Movie>()
            for (movieSearchResponse in listResponseBody) {
                if (movieSearchResponse.poster != null && movieSearchResponse.posterDetails != null) {

                    val movieSearch = Movie(
                        movieSearchResponse.id,
                        movieSearchResponse.title,
                        movieSearchResponse.poster,
                        movieSearchResponse.posterDetails,
                        movieSearchResponse.description,
                        movieSearchResponse.rating.toString(),
                        movieSearchResponse.date
                    )
                    listMovieSearch.add(movieSearch)
                }
            }
            return listMovieSearch
        }
    }
}