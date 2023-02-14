package com.example.offers.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.sharedcode.offers.presentation.OffersSharedViewModel
import kotlinx.coroutines.flow.last


class BaseOfferPagingSource(
    private val sharedViewModel: OffersSharedViewModel,
    private val paramms: TabsResponse.Data.Tab.Params?
) :
    PagingSource<Int, OffersResponse.Data.Outlet>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OffersResponse.Data.Outlet> {
        // Start refresh at page 60 if undefined.
        val offset = (params.key?.plus(60) ?: 60)
        val response = sharedViewModel.fetchOffers(paramms).last()
        return PagingHelper.getReturn(
            throwable = Throwable(),
            list = response,
            nextPage = offset.plus(1),
            pageSize = response.size,
            currentPage = offset,
            serverErrorMessage = "Server Error"
        )
    }
    override fun getRefreshKey(state: PagingState<Int, OffersResponse.Data.Outlet>): Int? {
        return 0
    }


}