package com.example.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState


class BasePagingSource<Item : Any>(
    val apiFun: suspend (offset: Int) -> List<Item>,
                                  ) : PagingSource<Int, Item>() {
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            // Start refresh at page 1 if undefined.
            val offset = (params.key?.plus(60) ?: 60)
            val response = apiFun(offset)
            
            LoadResult.Page(data = response,
                    prevKey = null,
                    nextKey = if(response.size < offset) null else offset.plus(1)
                           )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    
    override fun getRefreshKey(state: PagingState<Int, Item>): Int {
        return 0
    }
    
    
}