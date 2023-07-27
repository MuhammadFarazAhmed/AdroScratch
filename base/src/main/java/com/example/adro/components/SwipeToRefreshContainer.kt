package com.example.adro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToRefreshContainer(
    modifier: Modifier,
    pullRefreshState: PullRefreshState,
    isRefreshing: Boolean,
    content: @Composable () -> Unit,
) {
    Box(modifier.background(MaterialTheme.colorScheme.surface)) {

        content()

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}