package com.example.domain.usecase

import com.example.domain.models.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    
    suspend fun fetchHome(): Flow<HomeResponse>
}