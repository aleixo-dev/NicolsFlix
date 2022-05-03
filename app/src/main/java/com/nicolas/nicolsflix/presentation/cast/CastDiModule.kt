package com.nicolas.nicolsflix.presentation.cast

import com.nicolas.nicolsflix.network.MovieRepositoryV2
import com.nicolas.nicolsflix.network.MovieRepositoryV2Impl
import com.nicolas.nicolsflix.presentation.cast.ui.CastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CastDiModule {

    val instance = module {

        viewModel {
            CastViewModel(
                repository = get()
            )
        }

        factory<MovieRepositoryV2> {
            MovieRepositoryV2Impl(
                remote = get()
            )
        }
    }
}