package com.nicolas.nicolsflix.common

import com.nicolas.nicolsflix.BuildConfig

object Constants {

    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = BuildConfig.BASE_URL
    const val ENDPOINT_MOVIES_VIDEOS = "movie/{movie_id}/videos"
    const val ENDPOINT_PERSON_CREDITS = "movie/{movie_id}/credits"
    const val ENDPOINT_TRENDING = "trending/all/day"
    const val ENDPOINT_SEARCH = "search/movie"
    const val ENDPOINT_GENRE = "genre/movie/list"
    const val ENDPOINT_RECOMMEND = "movie/upcoming"
    const val ENDPOINT_POPULAR = "movie/popular"
    const val ENDPOINT_SIMILAR = "movie/{movie_id}/similar"
    const val ENDPOINT_DETAILS = "movie/{movie_id}"
    const val ENDPOINT_PERSON = "person/{person_id}"
    const val ENDPOINT_PERSON_MOVIE_CREDITS = "person/{person_id}/movie_credits"
    const val LANGUAGE_BR = "pt-BR"
    const val LANGUAGE_US = "en-US"

    const val LOAD_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
}