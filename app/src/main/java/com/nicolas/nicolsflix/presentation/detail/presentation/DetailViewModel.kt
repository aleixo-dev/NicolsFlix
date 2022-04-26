package com.nicolas.nicolsflix.presentation.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.network.MovieRepositoryV2
import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.nicolas.nicolsflix.network.models.remote.Trailers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repositoryV2: MovieRepositoryV2
) : ViewModel() {

    private val _casts = MutableLiveData<List<CastFromMovie>>()
    val casts: LiveData<List<CastFromMovie>> get() = _casts

    private val _trailers = MutableLiveData<List<Trailers>>()
    val trailers: LiveData<List<Trailers>> get() = _trailers

    fun getCastMovieDetail(movieId: Int) = viewModelScope.launch {

        val data = ArrayList<CastFromMovie>()

        repositoryV2.getCasts(movieId)
            .onStart { }
            .catch { }
            .collect { casts ->
                casts.map {
                    if (it.profilePath.isNullOrEmpty().not()) {
                        data.add(it)
                    }
                    _casts.value = data
                }
            }

    }

    fun getTrailerVideo(movieId: Int) = viewModelScope.launch {
        repositoryV2.getTrailerMovies(movieId)
            .onStart { }
            .catch { }
            .collect { trailers ->
                _trailers.value = trailers
            }
    }
}