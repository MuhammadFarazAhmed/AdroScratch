package com.example.sharedcode.offers.data.api

import com.example.sharedcode.common.CommonUtilsExtension
import com.example.sharedcode.common.CommonUtilsExtension.setDefaultParams
import com.example.sharedcode.domain.domain_model.HomeResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class MerchantApiImpl(private val httpClient: HttpClient) : MerchantApi {

    override suspend fun getHome(): HomeResponse =
        httpClient.post {
            url { path("/ets_api/v5/offer") }
            setDefaultParams(CommonUtilsExtension.API.OFFER)
        }.body() as HomeResponse
}