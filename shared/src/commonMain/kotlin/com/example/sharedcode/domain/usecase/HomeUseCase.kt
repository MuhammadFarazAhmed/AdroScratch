package com.example.sharedcode.domain.usecase

import com.example.sharedcode.domain.domain_model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    
    suspend fun fetchHome(): Flow<HomeResponse>
}