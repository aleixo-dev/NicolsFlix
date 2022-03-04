package com.nicolas.nicolsflix.upcoming.utils

import com.nicolas.nicolsflix.common.Resource
import kotlinx.coroutines.flow.Flow

interface UseCase<in Params, out Output> {
    suspend fun execute(params: Params? = null): Flow<Resource<Output>>
}