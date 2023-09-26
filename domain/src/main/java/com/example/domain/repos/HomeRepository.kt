package com.example.domain.repos

import com.example.domain.models.HomeResponse


interface HomeRepository {
    suspend fun fetchHome() : HomeResponse

}