package com.example.domain.usecase

import com.example.adro.models.HomeResponse

interface HomeUseCase {
    
    suspend fun fetchHome(): List<HomeResponse.Data.Section>
}