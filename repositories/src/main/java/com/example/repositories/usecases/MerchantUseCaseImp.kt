package com.example.repositories.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.adro.paging.BasePagingSource
import com.example.domain.models.ApiResult
import com.example.domain.models.MerchantDetailModel
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.repos.MerchantRepository
import com.example.domain.usecase.MerchantUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MerchantUseCaseImp(private val merchantRepository: MerchantRepository) :
    MerchantUseCase {

    override fun fetchTabs(params: HashMap<String, String>?): Flow<ApiResult<TabsResponse>> =
        merchantRepository.fetchTabs(params)

    override suspend fun fetchOffers(
        isRefreshing: MutableStateFlow<Boolean>,
        tabsParams: TabsResponse.Data.Tab.Params?,
        query: String?,
        queryType: String?,
        params: HashMap<String, String>
    ): Flow<PagingData<OffersResponse.Data.Outlet>> = Pager(PagingConfig(pageSize = 60)) {

        BasePagingSource(MutableStateFlow(true)) { offset ->
            merchantRepository.fetchOffers(
                query = query,
                queryType = queryType,
                tabsParams = tabsParams,
                params = params
            )
        }
    }.flow


    override suspend fun fetchMerchantDetail(
        merchantId: String,
        params: HashMap<String, String>
    ): Flow<ApiResult<MerchantDetailModel>> =
        merchantRepository.fetchMerchantDetail(merchantId, params)
}

