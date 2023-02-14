package com.example.offers.vm

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.offers.helper.BaseOfferPagingSource
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.sharedcode.offers.presentation.OffersSharedViewModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class OffersViewModel constructor(
    private val sharedViewModel: OffersSharedViewModel
) : ViewModel() {

    lateinit var offers: Flow<PagingData<OffersResponse.Data.Outlet>>

    init {
        sharedViewModel.fetchTabs {
            offers = Pager(PagingConfig(pageSize = 60)) {
                BaseOfferPagingSource(sharedViewModel, sharedViewModel.tabs.value?.get(0)?.params)
            }.flow.cachedIn(viewModelScope)
        }
    }


    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab?>?> =sharedViewModel.tabs

    val selectedTab = sharedViewModel.selectedTab


}