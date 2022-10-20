package com.example.sharedcode.domain.usecase

import com.example.adro.base.ApiResult
import com.example.sharedcode.model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    
    suspend fun fetchHome(): Flow<ApiResult<HomeResponse>>
}