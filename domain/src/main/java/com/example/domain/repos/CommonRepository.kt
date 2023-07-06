package com.example.domain.repos


import com.example.adro.common.Result
import com.example.adro.models.ApiResult
import com.example.adro.models.ConfigModel
import kotlinx.coroutines.flow.Flow

interface CommonRepository {

    suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>>

    fun getLanguage(): Flow<String>

    fun setLanguage(language: String)

}