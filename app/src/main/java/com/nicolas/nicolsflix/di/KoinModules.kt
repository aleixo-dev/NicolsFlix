package com.nicolas.nicolsflix.di

import androidx.room.Room
import com.nicolas.nicolsflix.data.db.NicolsDatabase
import com.nicolas.nicolsflix.data.network.RetrofitInitializer
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import com.nicolas.nicolsflix.repository.database.MovieDaoRepositoryImpl
import com.nicolas.nicolsflix.utils.Constants
import com.nicolas.nicolsflix.viewmodel.DetailsViewModel
import com.nicolas.nicolsflix.viewmodel.HomeViewModel
import com.nicolas.nicolsflix.viewmodel.MyListViewModel
import com.nicolas.nicolsflix.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModules = module {
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    single { RetrofitInitializer(retrofit = retrofit) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(), NicolsDatabase::class.java,
            "movies-nicols"
        ).build()
    }
    single { get<NicolsDatabase>().movieDao }
}

val homeModules = module {
    viewModel {
        HomeViewModel(
            MovieApiRepositoryImpl(
                movieApi = get()
            )
        )
    }
}

val myListModule = module {

    viewModel {
        MyListViewModel(
            MovieDaoRepositoryImpl(
                movieDao = get()
            )
        )
    }
}

val detailsModule = module {
    viewModel {
        DetailsViewModel(
            MovieDaoRepositoryImpl(
                movieDao = get()
            ), MovieApiRepositoryImpl(movieApi = get())
        )
    }
}

val searchModules = module {
    viewModel {
        SearchViewModel(
            MovieApiRepositoryImpl(
                movieApi = get()
            )
        )
    }
}
