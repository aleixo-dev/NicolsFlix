package com.nicolas.nicolsflix.upcoming.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain
import com.nicolas.nicolsflix.upcoming.domain.usecase.GetMovieUpcomingUseCase
import com.nicolas.nicolsflix.upcoming.utils.DataState
import kotlinx.coroutines.launch

class UpcomingViewModel(
    private val getMovieUpcomingUseCase: GetMovieUpcomingUseCase
) : ViewModel() {

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _movieUpcoming = MutableLiveData<DataState<List<UpcomingUiDomain>>>()
    val movieUpcoming: LiveData<DataState<List<UpcomingUiDomain>>> = _movieUpcoming.apply {
        value = DataState.Loading
    }

    fun getMovieComing() {
        viewModelScope.launch {
            try {
                val result = getMovieUpcomingUseCase.execute()
                _movieUpcoming.postValue(result)
            } catch (exception: Exception) {
                _movieUpcoming.postValue(DataState.Error())
            }
        }
    }
}