package com.example.repositories.repos

import com.example.adro.models.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension.API.OUTLET
import com.example.adro.common.CommonUtilsExtension.convert
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.models.FavoriteResponse
import com.example.adro.models.OffersResponse
import com.example.adro.models.TabsResponse
import com.example.adro.models.asList
import com.example.domain.repos.MerchantRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow

class MerchantRepositoryImp(
    private val client: HttpClient
) : MerchantRepository {

    override fun fetchTabs(params: HashMap<String, String>?): Flow<ApiResult<TabsResponse>> =
        convertToFlow(call = {
            client.post {
                setDefaultParams(OUTLET, params)
                url { path("/ets_api/v5/offer/tabs") }
            }
        }, success = {

        },
            failure = {}
        )

    override suspend fun fetchOffers(
        params: TabsResponse.Data.Tab.Params?,
        query: String?,
        queryType: String?
    ): List<OffersResponse.Data.Outlet> {

        return try {
            val response = client.post {

                setDefaultParams(
                    api = OUTLET,
                    additionalParams = params?.convert<TabsResponse.Data.Tab.Params, HashMap<String, String>>(),
                    params = hashMapOf<String, String>().apply {
                        if (queryType != null && query != null) {
                            put("query", query)
                            put("query_type", queryType)
                        }
                    }
                )

                url { path("/ets_api/v5/outlets") }
            }
            (response.body() as OffersResponse).data.outlets
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }

    }

    override suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet> =
        try {
            val response = client.post {
                setDefaultParams(OUTLET)
                url { path("/ets_api/v5/outlets") }
            }
            (response.body() as FavoriteResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }

}

