package com.nicolas.nicolsflix.data.network.api.popular

import com.nicolas.nicolsflix.common.Constants
import com.nicolas.nicolsflix.data.network.api.popular.response.MoviePopularResponseBody
import com.nicolas.nicolsflix.data.network.api.search.response.MovieSearchResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviePopularService {

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("language") language: String = Constants.LANGUAGE_BR,
        @Query("api_key") apiKey: String = "cead12b729988cec6e29f8bfd5d35116"
    ): Call<MoviePopularResponseBody>

}