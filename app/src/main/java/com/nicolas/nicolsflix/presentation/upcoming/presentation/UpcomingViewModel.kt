package com.nicolas.nicolsflix.presentation.upcoming.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.presentation.upcoming.domain.model.UpcomingUiDomain
import com.nicolas.nicolsflix.presentation.upcoming.domain.usecase.GetMovieUpcomingUseCase
import com.nicolas.nicolsflix.presentation.upcoming.utils.DataState
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