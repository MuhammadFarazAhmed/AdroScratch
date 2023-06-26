package com.example.domain.repos


import com.example.adro.models.ApiResult
import com.example.adro.models.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    
    suspend fun fetchHome() : Flow<ApiResult<HomeResponse>>
    
}