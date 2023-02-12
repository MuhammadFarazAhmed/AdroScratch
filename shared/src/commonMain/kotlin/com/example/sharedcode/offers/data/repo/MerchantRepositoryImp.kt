package com.example.sharedcode.offers.data.repo

import com.example.sharedcode.common.CommonFlowExtensions.convertToFlow
import com.example.sharedcode.common.CommonFlowExtensions.toCustomExceptions
import com.example.sharedcode.common.CommonUtilsExtension
import com.example.sharedcode.common.CommonUtilsExtension.setDefaultParams
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.sharedcode.domain.domain_model.asList
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

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet> {
        return try {
            val response = client.post {
                url { path("/ets_api/v5/outlets") }
                setDefaultParams(CommonUtilsExtension.API.OFFER)
            }
            (response.body() as OffersResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
    }

}

