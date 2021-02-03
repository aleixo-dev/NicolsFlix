package com.nicolas.nicolsflix.ui.mylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.repository.DatabaseDataSource
import kotlinx.coroutines.launch

class MyListViewModel(private val databaseDataSource: DatabaseDataSource) : ViewModel() {

    val myListMovie = MutableLiveData<List<Movie>>()

    fun getAllMyListMovie() {
        viewModelScope.launch {
            myListMovie.value = databaseDataSource.getAllMovie()
        }
    }

}