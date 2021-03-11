package com.nicolas.nicolsflix.di

import com.nicolas.nicolsflix.data.network.RetrofitInitializer
import com.nicolas.nicolsflix.data.repository.api.MovieRepositoryImpl
import com.nicolas.nicolsflix.ui.home.HomeViewModel
import com.nicolas.nicolsflix.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModules = module {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    single { RetrofitInitializer(retrofit = retrofit) }
}

val homeModules = module {
    viewModel {
        HomeViewModel(
            MovieRepositoryImpl(
                movieApi = get()
            )
        )
    }
}

val searchModules = module {
    viewModel {
        SearchViewModel(
            MovieRepositoryImpl(
                movieApi = get()
            )
        )
    }
}
