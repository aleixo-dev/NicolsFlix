package com.nicolas.nicolsflix.network.models.remote


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("cast")
    val castFromMovie: List<CastFromMovie>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)
