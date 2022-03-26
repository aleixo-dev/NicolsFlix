package com.nicolas.nicolsflix.upcoming.domain.usecase

import com.nicolas.nicolsflix.base.MainCoroutineRule
import com.nicolas.nicolsflix.common.Resource
import com.nicolas.nicolsflix.upcoming.data.repository.UpcomingRepository
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetMovieUpcomingUseCaseTest {

    private val upcomingRepository = mockk<UpcomingRepository>()

    private lateinit var useCase: GetMovieUpcomingUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        useCase = GetMovieUpcomingUseCase(upcomingRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return success when execute use case`() = runBlockingTest {
        val loading = Resource.Loading(true)
        val expected = Resource.Success(
            upcomingMockUseCase()
        )
        val collections = mutableListOf<Resource<List<UpcomingUiDomain>>>()

        coEvery { upcomingRepository.getUpcomingList() } returns upcomingMockUseCase()

        useCase.execute().collect {
            when (it) {
                is Resource.Loading -> {
                    collections.add(it)
                }
                is Resource.Success -> {
                    collections.add(it)
                }
                is Resource.Error -> {
                    collections.add(it)
                }
                is Resource.Empty -> {
                    collections.add(it)
                }
            }
        }

        assertEquals(loading, collections[0])
        assertEquals(expected, collections[1])
    }


    private fun upcomingMockUseCase() = listOf(
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