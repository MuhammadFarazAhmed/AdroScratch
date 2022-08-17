package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonExtensions.toResultFlow
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.repos.OffersRepository
import com.example.repositories.remote.api.OffersApi
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow

class OffersRepositoryImp(private val offersApi: OffersApi) : OffersRepository {

    override fun fetchTabs(): Flow<ApiResult<TabsResponse>> = toResultFlow { offersApi.tabs() }

    override fun fetchOffers(params: TabsResponse.Data.Tab.Params?): Flow<ApiResult<OffersResponse>> =
        toResultFlow { offersApi.offers(params.convert()) }

}

fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}
}
