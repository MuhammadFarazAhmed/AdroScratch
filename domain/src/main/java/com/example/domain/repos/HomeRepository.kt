package com.example.domain.repos



import com.example.adro.common.ApiResult
import com.example.domain.models.HomeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface HomeRepository {
    
    suspend fun fetchHome() : Flow<ApiResult<HomeResponse>>
    
}