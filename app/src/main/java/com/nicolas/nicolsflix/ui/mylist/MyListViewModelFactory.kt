package com.nicolas.nicolsflix.ui.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicolas.nicolsflix.data.repository.DatabaseDataSource

class MyListViewModelFactory(private val databaseDataSource: DatabaseDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyListViewModel(databaseDataSource) as T
    }
}