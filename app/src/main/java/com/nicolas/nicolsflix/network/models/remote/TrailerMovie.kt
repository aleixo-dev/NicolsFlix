package com.nicolas.nicolsflix.network.models.remote


import com.google.gson.annotations.SerializedName

data class TrailerMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val trailers: List<Trailers>
)