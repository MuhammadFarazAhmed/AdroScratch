package com.example.sharedcode.domain.usecase

import com.example.sharedcode.domain.domain_model.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    
    suspend fun fetchProfile(): Flow<ProfileResponse>
}