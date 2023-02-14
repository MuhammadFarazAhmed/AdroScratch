package com.example.sharedcode.offers.data.repo

import com.example.sharedcode.common.CommonUtilsExtension
import com.example.sharedcode.common.CommonUtilsExtension.setDefaultParams
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class MerchantRepositoryImp(
    private val client: HttpClient
) : MerchantRepository {

    override suspend fun fetchTabs(): TabsResponse =
        client.post {
            url { path("/ets_api/v5/offer/tabs") }
            setDefaultParams(CommonUtilsExtension.API.OFFER)
        }.body()

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): OffersResponse =
        client.post {
                url { path("/ets_api/v5/outlets") }
                setDefaultParams(CommonUtilsExtension.API.OFFER)
            }.body()
}

