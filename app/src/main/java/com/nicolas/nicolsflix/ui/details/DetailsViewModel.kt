package com.nicolas.nicolsflix.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.RetrofitInitializer
import com.nicolas.nicolsflix.data.network.api.popular.response.MoviePopularResponseBody
import com.nicolas.nicolsflix.data.network.mapper.MovieMapper
import com.nicolas.nicolsflix.data.repository.DatabaseDataSource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(private val databaseDataSource: DatabaseDataSource) : ViewModel() {

    val isMovieMyList = MutableLiveData<Boolean>()
    val listMovieSimilar = MutableLiveData<List<Movie>>()

    fun insertMovieMyList(movie: Movie) {
        viewModelScope.launch {
            databaseDataSource.insertMovie(movie)
        }
    }

    fun deleteMovieMyList(movie: Movie) {
        viewModelScope.launch {
            databaseDataSource.deleteMovie(movie)
        }
    }

    fun isMovieMyList(movie: Movie) {
        viewModelScope.launch {
            isMovieMyList.value = databaseDataSource.getAllMovie().contains(movie)
        }
    }

    fun getMovieSimilar(movieId: Int) {
        RetrofitInitializer.movieSimilarService().getMovieSimiliar(movieId).enqueue(object :
            Callback<MoviePopularResponseBody> {
            override fun onResponse(
                call: Call<MoviePopularResponseBody>,
                response: Response<MoviePopularResponseBody>
            ) {

                if (response.isSuccessful && response.code() != 34) {
                    listMovieSimilar.value = response.body()?.let {
                        MovieMapper.responseToDomain(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<MoviePopularResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}