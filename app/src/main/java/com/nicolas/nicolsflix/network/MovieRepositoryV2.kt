package com.nicolas.nicolsflix.network

import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.network.models.remote.CastDetail
import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.nicolas.nicolsflix.network.models.remote.MovieDetailsResponse
import com.nicolas.nicolsflix.network.models.remote.Trailers
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryV2 {

    suspend fun getCasts(movieId: Int): Flow<List<CastFromMovie>>
    suspend fun getTrailerMovies(movieId: Int): Flow<List<Trailers>>
    suspend fun getPersonDetail(personId: Int): Flow<CastDetail>
    suspend fun getTrendingMovie() : Flow<List<Movie>>
    suspend fun getDetailsMovie(movieId : Int) : Flow<MovieDetailsResponse>
}