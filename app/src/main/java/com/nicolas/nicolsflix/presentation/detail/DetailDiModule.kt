package com.nicolas.nicolsflix.presentation.detail

import com.nicolas.nicolsflix.network.MovieRepositoryV2
import com.nicolas.nicolsflix.network.MovieRepositoryV2Impl
import com.nicolas.nicolsflix.presentation.detail.presentation.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DetailDiModule {

    val instance = module {
        viewModel {
            DetailViewModel(
                repositoryV2 = get()
            )
        }

        //TODO: move this is separate module for network!

        factory<MovieRepositoryV2> {
            MovieRepositoryV2Impl(
                remote = get()
            )
        }
    }
}