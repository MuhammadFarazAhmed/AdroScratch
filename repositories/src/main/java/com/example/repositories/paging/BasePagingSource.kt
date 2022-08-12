package com.example.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState


class BasePagingSource<Item : Any>(
    val apiFun: suspend (page: Int, itemsPerPage: Int) -> List<Item>,
                                  ) : PagingSource<Int, Item>() {
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            val response = apiFun(nextPage, 10)
            
            LoadResult.Page(data = response,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if(response.isEmpty()) null else nextPage.plus(1)
                           )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    
    override fun getRefreshKey(state: PagingState<Int, Item>): Int {
        return 1
    }
    
    
}