package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultData
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.models.asList
import com.example.domain.repos.OffersRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class OffersRepositoryImp(
    private val client: HttpClient
) : OffersRepository {

    override fun fetchTabs(): Flow<ApiResult<TabsResponse>> =
        convertToFlow {
            client.post {
                url { path("/ets_api/v5/offer/tabs") }
                setDefaultData(CommonUtilsExtension.API.OFFER)
            }
        }

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet> {
        return try {
            val response = client.post {
                url { path("/ets_api/v5/outlets") }
                setDefaultData(CommonUtilsExtension.API.OFFER)
            }
            (response.body() as OffersResponse).asList()
        }catch (e:Exception){
            e.toCustomExceptions()
            emptyList()
        }
    }

}
