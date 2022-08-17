package com.example.offers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.adro.PagerExtension.pagerTabIndicatorOffset
import com.example.adro.common.CommonExtensions.collectAsStateLifecycleAware
import com.example.domain.models.TabsResponse
import com.example.offers.vm.OffersViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OffersScreen() {
    val vm = hiltViewModel<OffersViewModel>()

    val coroutineScope = rememberCoroutineScope()

    val tabs by vm.tabs.collectAsStateLifecycleAware()
    val pagerState = rememberPagerState(initialPage = 0)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Tabs(tabs, pagerState, coroutineScope) { tab ->
                vm.fetchOffers(tab)
            }
            Pager(tabs = tabs, pagerState = pagerState)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(
    tabs: List<TabsResponse.Data.Tab?>?,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    onTabSelect: (tab: TabsResponse.Data.Tab) -> Unit,
) {

    val tabIndex = pagerState.currentPage

    ScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = tabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = Color.Red
            )
        }) {
        tabs?.forEachIndexed { index, tab ->
            Tab(selected = tabIndex == index,
                modifier = Modifier.background(Color.Black),
                onClick = {
                    coroutineScope.launch {
                        if (tab != null) {
                            onTabSelect(tab)
                        }
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(text = tab?.name ?: "", color = Color.Red)
                })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(tabs: List<TabsResponse.Data.Tab?>?, pagerState: PagerState) {

    Surface(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            count = tabs?.size ?: 1
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tabs?.get(index)?.name ?: "",
                )
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun OffersScreenPreview() {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(TabsResponse.Data.Tab("All Offers"))
    val pagerState = rememberPagerState(initialPage = 0)
    Surface(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs, pagerState, coroutineScope){

        }
        Pager(tabs = tabs, pagerState = pagerState)
    }
}
