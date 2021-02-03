package com.nicolas.nicolsflix.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.network.RetrofitInitializer
import com.nicolas.nicolsflix.data.network.api.popular.response.MoviePopularResponseBody
import com.nicolas.nicolsflix.data.network.mapper.MovieMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val listMoviePopular = MutableLiveData<ArrayList<Movie>>()
    val listMovieTrending = MutableLiveData<ArrayList<Movie>>()
    val listMovieRecommend = MutableLiveData<ArrayList<Movie>>()

    fun callMoviePopular() {
        RetrofitInitializer.moviePopularService().getMoviePopular().enqueue(object :
            Callback<MoviePopularResponseBody> {
            override fun onResponse(
                call: Call<MoviePopularResponseBody>, response: Response<MoviePopularResponseBody>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listMoviePopular.value = MovieMapper.responseToDomain(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<MoviePopularResponseBody>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }

    fun callMovieTrending() {
        RetrofitInitializer.movieTrendingService().getMovieTrending().enqueue(object :
            Callback<MoviePopularResponseBody> {
            override fun onResponse(
                call: Call<MoviePopularResponseBody>, response: Response<MoviePopularResponseBody>
            ) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        listMovieTrending.value =
                            MovieMapper.responseToDomain(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<MoviePopularResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun callMovieRecommend() {

        RetrofitInitializer.movieRecommendService().getMovieRecommend().enqueue(object :
            Callback<MoviePopularResponseBody> {
            override fun onResponse(
                call: Call<MoviePopularResponseBody>,
                response: Response<MoviePopularResponseBody>
            ) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        listMovieRecommend.value = MovieMapper.responseToDomain(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<MoviePopularResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}