package com.example.adro.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.adro.common.CommonUtilsExtension.applyPagination
import com.example.adro.models.FavoriteResponse


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SwipeToRefreshContainer(
    modifier: Modifier,
    pullRefreshState: PullRefreshState,
    isRefreshing: Boolean,
    content: @Composable () -> Unit,
) {
    Box(modifier) {

            content()

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}