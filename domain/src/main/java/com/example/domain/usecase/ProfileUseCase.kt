package com.example.domain.usecase

import com.example.adro.base.ApiResult
import com.example.domain.models.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    
    suspend fun fetchProfile(): List<ProfileResponse.Data>
}