package com.example.domain.repos


import com.example.adro.models.HomeResponse

interface HomeRepository {
    
    suspend fun fetchHome() : List<HomeResponse.Data.Section>

}