package com.example.offers.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.CommonUtilsExtension.applyPagination
import com.example.adro.components.Header
import com.example.adro.models.OffersResponse
import com.example.adro.ui.ErrorItem
import com.example.offers.vm.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
    navigateToDetail: () -> Unit,
    params: HashMap<String, String> = hashMapOf(),
    vm: SearchViewModel = getViewModel()
) {

    val text by vm.query.collectAsStateLifecycleAware()
    val lazyOutlets = vm.offers.collectAsLazyPagingItems()

    LaunchedEffect(key1 = text) {
        val debounceDuration = 500L // Debounce duration in milliseconds
        var debounceJob: Job? = null

        vm.query.collectLatest {
            debounceJob?.cancel()
            debounceJob = launch {
                delay(debounceDuration)
                lazyOutlets.refresh()
            }
        }

    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column {

            Header(
                toolbarTitle = "Search",
                searchText = text,
                onQueryChange = { queryText -> vm.query.value = queryText },
                onCrossClicked = { if (text.isNotEmpty()) vm.query.value = "" }
            )

            OutletResults(lazyOutlets, navigateToDetail)

        }
    }
}

@Composable
private fun OutletResults(
    lazyOutlets: LazyPagingItems<OffersResponse.Data.Outlet>,
    navigateToDetail: () -> Unit
) {

    Column {

        LazyColumn {

            items(
                count = lazyOutlets.itemCount,
                key = lazyOutlets.itemKey(),
                contentType = lazyOutlets.itemContentType()
            ) { index ->

                val item = lazyOutlets[index]
                OutletItem(item, navigateToDetail = navigateToDetail)
                Divider(
                    color = Color.Black,
                    thickness = .5.dp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

            }

            applyPagination(lazyOutlets) {
                item {
                    ErrorItem(message = "No Result Found",
                        modifier = Modifier.fillParentMaxSize(),
                        onClickRetry = { })
                }
            }
        }

    }
}