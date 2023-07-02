package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adro.models.OffersResponse
import com.example.domain.usecase.MerchantUseCase
import com.example.adro.paging.BasePagingSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class SearchViewModel(
    application: Application,
    private val merchantUseCase: MerchantUseCase
) :
    AndroidViewModel(application) {

    val query = MutableStateFlow("")
    val offers: MutableStateFlow<PagingData<OffersResponse.Data.Outlet>> =
        MutableStateFlow(PagingData.empty())

    init {
        viewModelScope.launch {
            Pager(PagingConfig(pageSize = 60)) {
                BasePagingSource(MutableStateFlow(false)) {
                    merchantUseCase.fetchOffers(query = query.value, queryType = "name",)
                }
            }.flow.cachedIn(viewModelScope).collectLatest {
                offers.value = it
            }
        }
    }

}