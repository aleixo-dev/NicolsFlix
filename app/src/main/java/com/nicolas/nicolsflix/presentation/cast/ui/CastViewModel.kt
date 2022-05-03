package com.nicolas.nicolsflix.presentation.cast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.network.MovieRepositoryV2
import com.nicolas.nicolsflix.network.models.remote.CastDetail
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CastViewModel(
    private val repository: MovieRepositoryV2
) : ViewModel() {

    private val _person = MutableLiveData<CastDetail>()
    val person: LiveData<CastDetail> get() = _person

    fun getPersonDetail(personId: Int) = viewModelScope.launch {
        repository.getPersonDetail(personId)
            .onStart { }
            .catch { }
            .collect { person ->
                _person.value = person
            }
    }
}