package com.nicolas.nicolsflix.data.network.mapper

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.api.popular.response.MoviePopularResponseBody
import com.nicolas.nicolsflix.data.network.api.popular.response.Result

class MovieMapper {

    companion object {
        fun responseToDomain(listResponseBody: List<Result>): ArrayList<Movie> {

            val listMoviePopular = ArrayList<Movie>()
            for (popularMovie in listResponseBody) {
                if (popularMovie.title.isNullOrEmpty().not()) {
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
            }
            return listMoviePopular
        }
    }
}

fun Result.toMovie() = Movie(
    id,
    title,
    posterPath,
    backdropPath,
    overview,
    voteAverage.toString(),
    releaseDate
)

fun MoviePopularResponseBody.toMovieList() = MovieList(
    movies = results?.map { it.toMovie() } ?: emptyList()
)

data class MovieList(
    val movies: List<Movie>? = emptyList()
)
