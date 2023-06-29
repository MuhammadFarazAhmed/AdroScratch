package com.example.offers.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adro.models.ApiStatus
import com.example.adro.models.OffersResponse
import com.example.adro.models.TabsResponse
import com.example.domain.usecase.MerchantUseCase
import com.example.repositories.paging.BasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


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
                    merchantUseCase.fetchOffers(query = query.value, queryType = "name")
                }
            }.flow.cachedIn(viewModelScope).stateIn(viewModelScope).collectLatest {
                offers.value = it
            }
        }
    }

}