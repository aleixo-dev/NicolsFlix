package com.nicolas.nicolsflix.data.network.api.trending

import com.nicolas.nicolsflix.data.network.api.popular.response.MoviePopularResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieTrendingService {

    @GET("trending/all/day")
    fun getMovieTrending(
        @Query("api_key") apiKey: String = "cead12b729988cec6e29f8bfd5d35116",
        @Query("language") language: String = "pt-BR"
    ): Call<MoviePopularResponseBody>

}