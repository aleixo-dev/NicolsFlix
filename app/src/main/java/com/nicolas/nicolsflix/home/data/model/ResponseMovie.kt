package com.nicolas.nicolsflix.home.data.model

import com.google.gson.annotations.SerializedName
data class ResponseMovie(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResponseResultMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
