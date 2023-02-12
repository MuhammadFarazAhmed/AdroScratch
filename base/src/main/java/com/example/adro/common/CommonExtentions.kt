package com.example.adro.common

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.adro.ErrorItem
import com.example.adro.LoadingItem
import com.example.adro.LoadingView

object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}

fun <T : Any> LazyListScope.applyPagination(
    lazyLayout: LazyPagingItems<T>
) {
    lazyLayout.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
            }
            loadState.append is LoadState.Loading -> {
                item { LoadingItem() }
            }
            loadState.refresh is LoadState.Error -> {
                val e = lazyLayout.loadState.refresh as LoadState.Error
                item {
                    ErrorItem(message = e.error.message,
                        modifier = Modifier.fillParentMaxSize(),
                        onClickRetry = { })
                }
            }
            loadState.append is LoadState.Error -> {
                val e = lazyLayout.loadState.append as LoadState.Error
                item {
                    ErrorItem(
                        message = e.error.message, onClickRetry = {}
                    )
                }
            }
        }
    }
}
