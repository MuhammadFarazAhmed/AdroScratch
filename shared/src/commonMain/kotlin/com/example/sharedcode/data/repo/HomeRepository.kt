package com.example.sharedcode.data.repo


import com.example.sharedcode.domain.domain_model.HomeResponse

interface HomeRepository {
    
    suspend fun fetchHome() : HomeResponse

}