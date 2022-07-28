package com.nicolas.nicolsflix.presentation.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.network.MovieRepositoryV2
import com.nicolas.nicolsflix.presentation.home.domain.domain.MovieUiDomain
import com.nicolas.nicolsflix.presentation.home.domain.usecase.GetMoviePopularUseCase
import com.nicolas.nicolsflix.repository.api.MovieApiRepository
import com.nicolas.nicolsflix.upcoming.utils.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieApiRepository: MovieApiRepository,
    private val movieRepositoryV2: MovieRepositoryV2,
    private val getMoviePopularUseCase: GetMoviePopularUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    private val _screenState = MutableLiveData<HomeState>()
    val screenState: LiveData<HomeState> get() = _screenState

    private val _moviePopularList = MutableLiveData<DataState<List<MovieUiDomain>>>()
    val moviePopularList: LiveData<DataState<List<MovieUiDomain>>> = _moviePopularList.apply {
        value = DataState.Loading
    }

    private val _listTrendingMovie = MutableLiveData<ArrayList<Movie>>()
    val listTrendingMovie: LiveData<ArrayList<Movie>> = _listTrendingMovie

    private val _listNamesMovie = MutableLiveData<List<Movie>>()
    val listNamesMovie: LiveData<List<Movie>> = _listNamesMovie

    private val _listPopularMovie = MutableLiveData<ArrayList<Movie>>()
    val listPopularMovie: LiveData<ArrayList<Movie>> = _listPopularMovie

    fun interact(event: HomeEvent) {
        when (event) {
            HomeEvent.OnOpened -> {
                fetchTrendingMovies()
                getMoviePopular()
                fetchPopularMovie()
            }
        }
    }

    private fun getMoviePopular() {
        viewModelScope.launch {
            try {
                val result = getMoviePopularUseCase.execute()
                _moviePopularList.postValue(result)
            } catch (exception: Exception) {
                _moviePopularList.postValue(DataState.Error())
            }
        }
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            movieRepositoryV2.getTrendingMovie()
                .onStart {
                    _state.value = State.Loading
                    setState(HomeState.Loading)
                }
                .catch {
                    _state.value = State.Error(it.message)
                    setState(HomeState.Error(it.message))
                }
                .collect { movies ->
                    _state.value = State.Success(movies)
                    setState(HomeState.Success(movies))
                }
        }
    }

    fun fetchPopularMovie() {
        viewModelScope.launch {
            _listPopularMovie.value = movieApiRepository.getPopularMovie()
        }
    }

    fun fetchNameMovie(titleMovie: String) {
        viewModelScope.launch {
            movieApiRepository.getMovieSearch(titleMovie)
                .onStart { Log.d("NICONICO", "fetchNameMovie: loading ") }
                .catch { Log.d("NICONICO", "fetchNameMovie: $it ") }
                .collect {
                    Log.d("NICONICO", "fetchNameMovie: $it ")
                    _listNamesMovie.value = it
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val movies: List<Movie>) : State()
        data class Error(val error: String?) : State()
    }

    private fun setState(newState: HomeState) {
        _screenState.value = newState
    }
}