package com.example.adro.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.Flow


class BasePagingSource<Item : Any>(
    private val isRefreshing : MutableStateFlow<Boolean> , private val apiFun: suspend (offset: Int) -> List<Item>,
                                  ) : PagingSource<Int, Item>() {
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            // Start refresh at page 1 if undefined.
            val offset = (params.key?.plus(60) ?: 60)
            isRefreshing.emit(true)
            val response = apiFun(offset)
            isRefreshing.emit(false)
            LoadResult.Page(data = response,
                    prevKey = null,
                    nextKey = if(response.size < offset) null else offset.plus(1)
                           )
        } catch (e: Exception) {
            isRefreshing.emit(false)
            LoadResult.Error(e)
        }
    }
    
    override fun getRefreshKey(state: PagingState<Int, Item>): Int {
        return 0
    }
    
    
}