package com.nicolas.nicolsflix.upcoming.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.common.Resource
import com.nicolas.nicolsflix.common.ViewState
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain
import com.nicolas.nicolsflix.upcoming.domain.usecase.GetMovieUpcomingUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UpcomingViewModel(
    private val getMovieUpcomingUseCase: GetMovieUpcomingUseCase
) : ViewModel() {

    private val _movieUpcoming = MutableLiveData<ViewState<List<UpcomingUiDomain>>>()
    val movieUpcoming: LiveData<ViewState<List<UpcomingUiDomain>>> = _movieUpcoming

    fun getMovieComing() {
        viewModelScope.launch {
            _movieUpcoming.value = ViewState.Loading()
            getMovieUpcomingUseCase.execute().collect {
                when (it) {
                    is Resource.Loading -> {
                        _movieUpcoming.value = ViewState.Loading()
                    }
                    is Resource.Success -> {
                        _movieUpcoming.value = ViewState.Success(it.item)
                    }
                    is Resource.Error -> {
                        _movieUpcoming.value = ViewState.Error(it.throwable)
                    }
                    is Resource.Empty -> {

                    }
                }
            }
        }
    }
}
