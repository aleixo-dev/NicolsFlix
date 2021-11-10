package com.nicolas.nicolsflix.presentation.upcoming.data.repository

import com.nicolas.nicolsflix.presentation.upcoming.domain.model.UpcomingUiDomain

interface UpcomingRepository {

    suspend fun getUpcomingList(): List<UpcomingUiDomain>

}