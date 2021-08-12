package com.nicolas.nicolsflix.di

import androidx.room.Room
import com.nicolas.nicolsflix.data.db.NicolsDatabase
import com.nicolas.nicolsflix.data.network.RetrofitInitializer
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import com.nicolas.nicolsflix.repository.database.MovieDaoRepositoryImpl
import com.nicolas.nicolsflix.service.MovieService
import com.nicolas.nicolsflix.viewmodel.DetailsViewModel
import com.nicolas.nicolsflix.viewmodel.HomeViewModel
import com.nicolas.nicolsflix.viewmodel.MyListViewModel
import com.nicolas.nicolsflix.viewmodel.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModules = module {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()

    single { RetrofitInitializer(retrofit = retrofit) }
    single { provideRetrofitService(get()) }
    single { provideOkHttpClient() }
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

fun provideOkHttpClient(): OkHttpClient {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideRetrofitService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)
