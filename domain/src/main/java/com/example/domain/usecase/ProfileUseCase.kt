package com.example.domain.usecase

import com.example.domain.models.ProfileResponse

interface ProfileUseCase {
    
    suspend fun fetchProfile(): List<ProfileResponse.Data>
}