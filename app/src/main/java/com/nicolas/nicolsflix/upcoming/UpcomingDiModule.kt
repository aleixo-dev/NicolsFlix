package com.nicolas.nicolsflix.upcoming

import com.nicolas.nicolsflix.upcoming.data.datasource.remote.UpcomingApi
import com.nicolas.nicolsflix.upcoming.data.datasource.remote.UpcomingRemoteDataSource
import com.nicolas.nicolsflix.upcoming.data.datasource.remote.UpcomingRemoteDataSourceImpl
import com.nicolas.nicolsflix.upcoming.data.repository.UpcomingRepository
import com.nicolas.nicolsflix.upcoming.data.repository.UpcomingRepositoryImpl
import com.nicolas.nicolsflix.upcoming.domain.usecase.GetMovieUpcomingUseCase
import com.nicolas.nicolsflix.upcoming.presentation.UpcomingViewModel
import com.nicolas.nicolsflix.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object UpcomingDiModule {

    val instance = module {

        single { provideRetrofit() }
        single { provideRetrofitService(get()) }
        single { provideOkHttp() }

        factory<UpcomingRemoteDataSource> {
            UpcomingRemoteDataSourceImpl(
                api = get()
            )
        }

        factory<UpcomingRepository> {
            UpcomingRepositoryImpl(
                remoteDataSource = get()
            )
        }

        factory {
            GetMovieUpcomingUseCase(
                repository = get()
            )
        }

        viewModel {
            UpcomingViewModel(
                getMovieUpcomingUseCase = get()
            )
        }
    }

    private fun provideRetrofitService(retrofit: Retrofit): UpcomingApi =
        retrofit.create(UpcomingApi::class.java)

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