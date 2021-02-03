package com.nicolas.nicolsflix.data.network

import com.nicolas.nicolsflix.data.network.api.popular.MoviePopularService
import com.nicolas.nicolsflix.data.network.api.recommend.MovieRecommendService
import com.nicolas.nicolsflix.data.network.api.similiar.MovieSimilarService
import com.nicolas.nicolsflix.data.network.api.trending.MovieTrendingService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializer {

    private fun initRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun moviePopularService(): MoviePopularService =
        initRetrofit().create(MoviePopularService::class.java)

    fun movieTrendingService(): MovieTrendingService =
        initRetrofit().create(MovieTrendingService::class.java)

    fun movieRecommendService(): MovieRecommendService =
        initRetrofit().create(MovieRecommendService::class.java)

    fun movieSimilarService(): MovieSimilarService =
        initRetrofit().create(MovieSimilarService::class.java)
}