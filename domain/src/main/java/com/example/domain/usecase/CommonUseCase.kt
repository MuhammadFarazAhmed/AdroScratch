package com.example.domain.usecase

import com.example.domain.models.ApiResult
import com.example.domain.models.HomeResponse
import kotlinx.coroutines.flow.Flow

interface CommonUseCase {
    
    suspend fun fetchConfig(): Flow<ApiResult<HomeResponse>>
}