package com.nicolas.nicolsflix.data.network

import com.nicolas.nicolsflix.data.network.api.popular.MoviePopularService
import com.nicolas.nicolsflix.data.network.api.recommend.MovieRecommendService
import com.nicolas.nicolsflix.data.network.api.search.MovieSearchService
import com.nicolas.nicolsflix.data.network.api.similiar.MovieSimilarService
import com.nicolas.nicolsflix.data.network.api.trending.MovieTrendingService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer(private val retrofit: Retrofit) {

    fun movieTrendingService(): MovieTrendingService =
        retrofit.create(MovieTrendingService::class.java)

    fun movieSimilarService(): MovieSimilarService =
        retrofit.create(MovieSimilarService::class.java)

    fun movieSearchService(): MovieSearchService = retrofit.create(MovieSearchService::class.java)
}