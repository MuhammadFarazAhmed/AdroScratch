package com.example.repositories.repos

import com.example.domain.models.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension.API.OUTLET
import com.example.adro.common.CommonUtilsExtension.convert
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.models.asList
import com.example.domain.repos.MerchantRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow

class MerchantRepositoryImp(
    private val client: HttpClient
) : MerchantRepository {

    override fun fetchTabs(params: HashMap<String, String>?): Flow<ApiResult<TabsResponse>> =
        convertToFlow {
            client.post {
                setDefaultParams(OUTLET, params)
                url { path("/ets_api/v5/offer/tabs") }
            }
        }

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet> {
        return try {
            val response = client.post {
                setDefaultParams(
                    OUTLET,
                    params?.convert<TabsResponse.Data.Tab.Params, HashMap<String, String>>()
                )
                url { path("/ets_api/v5/outlets") }
            }
            (response.body() as OffersResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
    }

}

