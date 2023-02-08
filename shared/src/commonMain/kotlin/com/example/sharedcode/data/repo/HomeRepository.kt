package com.example.sharedcode.data.repo


import com.example.sharedcode.common.Result
import com.example.sharedcode.domain.domain_model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    
    suspend fun fetchHome() : HomeResponse

}