package com.nicolas.nicolsflix.home.domain.domain

import java.io.Serializable

data class MovieUiDomain(
    val id: String,
    val title: String,
    val description: String,
    val imagePoster: String,
    val imageBackground: String,
    val rating: String,
    val date: String
) : Serializable