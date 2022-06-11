package com.nicolas.nicolsflix.presentation.home

import com.nicolas.nicolsflix.presentation.home.data.datasource.remote.MovieApi
import com.nicolas.nicolsflix.presentation.home.data.datasource.remote.MovieRemoteDataSource
import com.nicolas.nicolsflix.presentation.home.data.datasource.remote.MovieRemoteDataSourceImpl
import com.nicolas.nicolsflix.presentation.home.data.repository.MovieRepository
import com.nicolas.nicolsflix.presentation.home.data.repository.MovieRepositoryImpl
import com.nicolas.nicolsflix.presentation.home.domain.usecase.GetMoviePopularUseCase
import com.nicolas.nicolsflix.presentation.home.presentation.HomeViewModel
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import com.nicolas.nicolsflix.common.Constants
import com.nicolas.nicolsflix.network.MovieRepositoryV2Impl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HomeDiModule {

    val instance = module {
        viewModel {
            HomeViewModel(
                MovieApiRepositoryImpl(movieApi = get()),
                movieRepositoryV2 = get(),
                getMoviePopularUseCase = get()
            )
        }

        single { provideRetrofitService(get()) }

        factory {
            GetMoviePopularUseCase(repository = get())
        }

        factory<MovieRepository> {
            MovieRepositoryImpl(
                remoteDataSource = get()
            )
        }

        factory<MovieRemoteDataSource> {
            MovieRemoteDataSourceImpl(
                api = get()
            )
        }
    }

    private fun provideRetrofitService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}