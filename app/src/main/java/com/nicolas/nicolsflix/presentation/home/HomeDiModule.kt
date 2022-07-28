package com.nicolas.nicolsflix.presentation.home

import com.nicolas.nicolsflix.presentation.home.data.datasource.remote.MovieApi
import com.nicolas.nicolsflix.presentation.home.data.datasource.remote.MovieRemoteDataSource
import com.nicolas.nicolsflix.presentation.home.data.datasource.remote.MovieRemoteDataSourceImpl
import com.nicolas.nicolsflix.presentation.home.data.repository.MovieRepository
import com.nicolas.nicolsflix.presentation.home.data.repository.MovieRepositoryImpl
import com.nicolas.nicolsflix.presentation.home.domain.usecase.GetMoviePopularUseCase
import com.nicolas.nicolsflix.presentation.home.presentation.HomeViewModel
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeDiModule {

    val instance = module {
        viewModel {
            HomeViewModel(
                MovieApiRepositoryImpl(
                    movieApi = get(),
                    coroutineDispatcher = Dispatchers.IO
                ),
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