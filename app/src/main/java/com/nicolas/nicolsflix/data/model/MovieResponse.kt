package com.nicolas.nicolsflix.data.model

import com.google.gson.annotations.SerializedName
import com.nicolas.nicolsflix.data.db.entity.MovieEntity

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val poster: String?,
    @SerializedName("backdrop_path") val posterDetails: String?,
    @SerializedName("overview") val description: String,
    @SerializedName("vote_average") val rating: Double,
    @SerializedName("release_date") val date: String

)

fun MovieResponse.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        poster = this.poster,
        posterDetails = this.posterDetails,
        description = this.description,
        rating = this.rating.toString(),
        date = this.date
    )
}
