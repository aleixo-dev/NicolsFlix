package com.nicolas.nicolsflix.data.network.api.search

import com.nicolas.nicolsflix.data.network.api.search.response.MovieSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchService {

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") searchMovie : String?,
        @Query("api_key") apiKey : String = "cead12b729988cec6e29f8bfd5d35116",
        @Query("language") language : String = "pt-BR"
    ) : MovieSearchResult
}