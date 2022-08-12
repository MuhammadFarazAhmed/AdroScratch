package com.example.repositories.remote.api

import com.example.home.ui.models.HomeResponse
import com.example.repositories.security.CLibController
import retrofit2.http.POST
import retrofit2.http.Url

interface HomeApi {

    @POST("ets_api/v5/home")
    suspend fun home(@Url baseUrl: String = CLibController.getENTBaseUrlOnline()): HomeResponse
}