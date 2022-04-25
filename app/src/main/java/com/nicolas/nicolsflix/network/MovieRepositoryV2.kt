package com.nicolas.nicolsflix.network

import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.nicolas.nicolsflix.network.models.remote.Trailers
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryV2 {

    suspend fun getCasts(movieId : Int) : Flow<List<CastFromMovie>>

    suspend fun getTrailerMovies(movieId : Int) : Flow<List<Trailers>>

}