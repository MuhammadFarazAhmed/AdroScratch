package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.models.asList
import com.example.domain.repos.MerchantRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class MerchantRepositoryImp(
    private val client: HttpClient
) : MerchantRepository {

    override fun fetchTabs(params: HashMap<String, String>?): Flow<ApiResult<TabsResponse>> =
        convertToFlow {
            client.post {
                url { path("/ets_api/v5/offer/tabs") }
                setDefaultParams(CommonUtilsExtension.API.OUTLET)
            }
        }

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet> {
        return try {
            val response = client.post {
                url { path("/ets_api/v5/outlets") }
                setBody(params)
                setDefaultParams(CommonUtilsExtension.API.OUTLET)
            }
            (response.body() as OffersResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
    }

}

