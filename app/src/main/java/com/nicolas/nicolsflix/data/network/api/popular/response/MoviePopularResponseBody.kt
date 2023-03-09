package com.nicolas.nicolsflix.data.network.api.popular.response


import com.google.gson.annotations.SerializedName

data class MoviePopularResponseBody(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)