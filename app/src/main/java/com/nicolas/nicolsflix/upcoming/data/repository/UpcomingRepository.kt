package com.nicolas.nicolsflix.upcoming.data.repository

import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain

interface UpcomingRepository {

    suspend fun getUpcomingList(): List<UpcomingUiDomain>

}