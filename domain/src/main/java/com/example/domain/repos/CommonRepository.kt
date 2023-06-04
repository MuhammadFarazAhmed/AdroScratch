package com.example.domain.repos


import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import kotlinx.coroutines.flow.Flow

interface CommonRepository {
    
    suspend fun fetchConfig() : Flow<ApiResult<ConfigModel>>

}