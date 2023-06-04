package com.example.domain.repos


import com.example.domain.models.ApiResult
import com.example.domain.models.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    
    suspend fun fetchHome() : Flow<ApiResult<HomeResponse>>
    
}