package com.nicolas.nicolsflix.home

import com.nicolas.nicolsflix.home.data.datasource.remote.MovieApi
import com.nicolas.nicolsflix.home.data.datasource.remote.MovieRemoteDataSource
import com.nicolas.nicolsflix.home.data.datasource.remote.MovieRemoteDataSourceImpl
import com.nicolas.nicolsflix.home.data.repository.MovieRepository
import com.nicolas.nicolsflix.home.data.repository.MovieRepositoryImpl
import com.nicolas.nicolsflix.home.domain.usecase.GetMoviePopularUseCase
import com.nicolas.nicolsflix.home.presentation.HomeViewModel
import com.nicolas.nicolsflix.repository.api.MovieApiRepositoryImpl
import com.nicolas.nicolsflix.common.Constants
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
                GetMoviePopularUseCase(repository = get())
            )
        }

        single { provideRetrofitService(get()) }
        single { provideRetrofit() }
        single { provideOkHttp() }

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

    private fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()

    private fun provideOkHttp(): OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}