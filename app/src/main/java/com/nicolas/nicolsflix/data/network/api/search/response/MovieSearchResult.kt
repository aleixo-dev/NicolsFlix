package com.nicolas.nicolsflix.data.network.api.search.response

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(@SerializedName("results") val results: List<MovieSearchResponseBody>)