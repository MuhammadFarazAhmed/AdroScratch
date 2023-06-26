package com.example.domain.usecase

import com.example.adro.models.ApiResult
import com.example.adro.models.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    
    suspend fun fetchHome(): Flow<ApiResult<HomeResponse>>
}