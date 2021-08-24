package com.nicolas.nicolsflix.upcoming.domain.model

import java.io.Serializable

data class UpcomingUiDomain(
    val id: String,
    val title: String,
    val imagePoster: String? = null ,
    val description: String,
    val releaseDate: String,
    val vote: String
) : Serializable
