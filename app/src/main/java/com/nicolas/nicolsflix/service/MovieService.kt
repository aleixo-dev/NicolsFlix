package com.nicolas.nicolsflix.service

import com.nicolas.nicolsflix.data.model.GenreResponse
import com.nicolas.nicolsflix.data.model.MovieResponse
import com.nicolas.nicolsflix.data.network.api.search.response.MovieSearchResult
import com.nicolas.nicolsflix.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(Constants.ENDPOINT_POPULAR)
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieResponse>

    @GET(Constants.ENDPOINT_GENRE)
    suspend fun getGenreMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<GenreResponse>

    @GET(Constants.ENDPOINT_RECOMMEND)
    suspend fun getRecommendMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieResponse>

    @GET(Constants.ENDPOINT_SIMILAR)
    suspend fun getSimilarMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieResponse>

    @GET(Constants.ENDPOINT_TRENDING)
    suspend fun getTrendingMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE_BR
    ): Response<MovieResponse>

    @GET(Constants.ENDPOINT_SEARCH)
    suspend fun getSearchMovie(
        @Query("query") nameMovie: String,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE_BR,
    ): Response<MovieSearchResult>
}