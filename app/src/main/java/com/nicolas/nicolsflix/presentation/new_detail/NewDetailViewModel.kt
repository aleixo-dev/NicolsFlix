package com.nicolas.nicolsflix.presentation.new_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.network.MovieRepositoryV2
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class NewDetailViewModel(
    private val repositoryV2: MovieRepositoryV2
) : ViewModel() {

    private val _state = MutableLiveData<NewDetailState>()
    val state: LiveData<NewDetailState> get() = _state

    fun interact(event: NewDetailEvent) {
        when (event) {
            is NewDetailEvent.OnOpened -> {
                event.movieId?.let {
                    getMovieDetails(it)
                }
            }
        }
    }

    // id movie: 436270
    private fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        repositoryV2.getDetailsMovie(movieId)
            .zip(repositoryV2.getCasts(movieId)) { first, second ->
                return@zip Pair(first, second)
            }
            .onStart { setState(NewDetailState.Loading) }
            .catch { setState(NewDetailState.Error) }
            .collect {
                setState(NewDetailState.SetupViews(details = it.first, cast = it.second))
            }
    }

    private fun setState(newState: NewDetailState) {
        _state.value = newState
    }
}