package com.example.repositories.remote.api

import com.example.domain.models.HomeResponse
import retrofit2.Response
import retrofit2.http.*

interface HomeApi {
    @POST("ets_api/v5/home")
    suspend fun home(): Response<HomeResponse>
}