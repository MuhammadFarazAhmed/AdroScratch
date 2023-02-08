package com.example.sharedcode.domain.usecase

import com.example.sharedcode.domain.domain_model.Home
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    
    suspend fun fetchHome() : Flow<List<Home>>
}