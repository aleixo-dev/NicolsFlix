package com.nicolas.nicolsflix.data.network.mapper

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.api.search.response.MovieSearchResponseBody
import com.nicolas.nicolsflix.data.network.api.search.response.MovieSearchResult
import com.nicolas.nicolsflix.models.MovieSearch
import com.nicolas.nicolsflix.models.MovieSearchList

class MovieSearchMapper {
    companion object {
        fun responseToDomain(listResponseBody: List<MovieSearchResponseBody>): ArrayList<Movie> {

            val listMovieSearch = ArrayList<Movie>()
            for (movieSearchResponse in listResponseBody) {
                if (movieSearchResponse.poster != null && movieSearchResponse.posterDetails != null && movieSearchResponse.description != "" && movieSearchResponse.date != "") {

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

fun MovieSearchResult.toDomain() = MovieSearchList(
    searchMovies = results?.map { it.toDomain() } ?: emptyList()
)

fun MovieSearchResponseBody.toDomain() = Movie(
    id = id,
    title = title,
    poster = poster,
    posterDetails = posterDetails,
    description = description,
    rating = rating.toString(),
    date = date
)


