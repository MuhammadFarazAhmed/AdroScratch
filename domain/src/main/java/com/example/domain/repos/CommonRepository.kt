package com.example.domain.repos


import com.example.adro.base.ApiResult
import com.example.domain.models.ConfigModel
import kotlinx.coroutines.flow.Flow

interface CommonRepository {
    
    suspend fun fetchConfig() : Flow<ApiResult<ConfigModel>>

}