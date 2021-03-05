package com.nicolas.nicolsflix.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicolas.nicolsflix.data.repository.database.DatabaseDataSource

class DetailsViewModelFactory(
    private val databaseDataSource: DatabaseDataSource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(databaseDataSource) as T
    }
}