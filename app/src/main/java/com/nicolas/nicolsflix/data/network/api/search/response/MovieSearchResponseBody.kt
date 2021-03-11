package com.nicolas.nicolsflix.data.network.api.search.response

import com.google.gson.annotations.SerializedName

data class MovieSearchResponseBody(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val poster: String?,
    @SerializedName("backdrop_path") val posterDetails: String?,
    @SerializedName("overview") val description: String,
    @SerializedName("vote_average") val rating: Double,
    @SerializedName("release_date") val date: String

)