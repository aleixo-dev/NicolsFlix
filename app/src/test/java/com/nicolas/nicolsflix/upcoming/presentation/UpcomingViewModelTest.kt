package com.nicolas.nicolsflix.upcoming.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nicolas.nicolsflix.base.MainCoroutineRule
import com.nicolas.nicolsflix.common.ErrorType
import com.nicolas.nicolsflix.common.Resource
import com.nicolas.nicolsflix.common.ViewState
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain
import com.nicolas.nicolsflix.upcoming.domain.usecase.GetMovieUpcomingUseCase
import com.nicolas.nicolsflix.upcoming.utils.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UpcomingViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val getMovieUpcomingUseCase = mockk<GetMovieUpcomingUseCase>()

    private lateinit var upcomingViewModel: UpcomingViewModel

    @Before
    fun setUp() {
        upcomingViewModel = UpcomingViewModel(getMovieUpcomingUseCase)
    }

    @Test
    fun `should return successful when call getMovieUpcomingUseCase`() {

        val loading = ViewState.Loading<UpcomingUiDomain>()
        val expectedResult = ViewState.Success(getUpcomingUiDomainMock())
        val collect = mutableListOf<ViewState<List<UpcomingUiDomain>>>()

        upcomingViewModel.movieUpcoming.observeForever { collect.add(it) }

        coEvery { getMovieUpcomingUseCase.execute() } returns flowOf(
            Resource.Success(
                getUpcomingUiDomainMock()
            )
        )

        upcomingViewModel.getMovieComing()

        assertEquals(loading, collect[0])
        assertEquals(expectedResult, collect[1])

        coVerify(exactly = 1) {
            getMovieUpcomingUseCase.execute()
        }

        confirmVerified(getMovieUpcomingUseCase)
    }

    @Test
    fun `should return error when calling use case`() {

        val loading = ViewState.Loading<UpcomingUiDomain>()
        val result = ViewState.Error<UpcomingUiDomain>("")
        val collection = mutableListOf<ViewState<List<UpcomingUiDomain>>>()

        upcomingViewModel.movieUpcoming.observeForever { collection.add(it) }

        coEvery { getMovieUpcomingUseCase.execute() } returns flowOf(
            Resource.Error(
                ""
            )
        )

        upcomingViewModel.getMovieComing()

        assertEquals(loading, collection[0])
        assertEquals(result, collection[1])

        coVerify(exactly = 1) {
            getMovieUpcomingUseCase.execute()
        }

        confirmVerified(getMovieUpcomingUseCase)

    }

    private fun getUpcomingUiDomainMock() = listOf(
        UpcomingUiDomain(
            "1",
            "title1",
            "image1",
            "description1",
            "date1",
            "vote1"
        ),
        UpcomingUiDomain(
            "2",
            "title2",
            "image2",
            "description2",
            "date2",
            "vote2"
        ),
    )
}