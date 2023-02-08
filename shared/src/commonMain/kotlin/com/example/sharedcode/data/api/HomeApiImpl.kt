package com.example.sharedcode.data.api

import com.example.sharedcode.common.CommonUtilsExtension
import com.example.sharedcode.common.CommonUtilsExtension.setDefaultParams
import com.example.sharedcode.domain.domain_model.HomeResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class HomeApiImpl(private val httpClient: HttpClient) : HomeApi {


    override suspend fun getHome(): HomeResponse = httpClient.post {
        url { path("/ets_api/v5/home") }
        setDefaultParams(CommonUtilsExtension.API.HOME)
    }.body()


}