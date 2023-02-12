package com.example.offers.vm

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.offers.common.BasePagingSource
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.sharedcode.offers.presentation.OffersSharedViewModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow


class OffersViewModel constructor(
    private val sharedViewModel: OffersSharedViewModel
) : ViewModel() {

    init {
        fetchTabs()
    }

    private fun fetchTabs() {
        sharedViewModel.fetchTabs()
    }

    val offers = Pager(PagingConfig(pageSize = 60)) {
        BasePagingSource {
            sharedViewModel.offers.value
        }
    }.flow.cachedIn(viewModelScope)

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab?>?> = sharedViewModel.tabs

    val selectedTab = sharedViewModel.selectedTab


}