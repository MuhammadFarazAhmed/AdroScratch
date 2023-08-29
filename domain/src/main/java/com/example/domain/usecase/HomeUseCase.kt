package com.example.domain.usecase

import com.example.domain.models.HomeResponse


interface HomeUseCase {
    
    suspend fun fetchHome(): List<HomeResponse.Data.Section>
}