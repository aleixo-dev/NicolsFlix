package com.nicolas.nicolsflix.data.network.api.recommend

import com.nicolas.nicolsflix.data.network.api.popular.response.MoviePopularResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRecommendService {

    @GET("movie/upcoming")
    fun getMovieRecommend(
        @Query("api_key") apiKey: String = "cead12b729988cec6e29f8bfd5d35116"
    ): Call<MoviePopularResponseBody>

}