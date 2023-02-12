package com.example.offers.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.adro.PagerExtension.pagerTabIndicatorOffset
import com.example.adro.common.applyPagination
import com.example.adro.common.collectAsStateLifecycleAware
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.offers.vm.OffersViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OffersScreen(navigateToDetail: ()->Unit,vm: OffersViewModel = get()) {

    val coroutineScope = rememberCoroutineScope()

    val tabs by vm.tabs.collectAsStateLifecycleAware()

    val pagerState = rememberPagerState(initialPage = 0)

    Surface(modifier = Modifier.fillMaxSize()) {

        Column {

            Tabs(tabs, pagerState, coroutineScope) { tab ->
                vm.selectedTab.value = tab
            }

            Pager(tabs = tabs, pagerState = pagerState, vm,navigateToDetail)
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
        backgroundColor = Color.Black,
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
fun Pager(
    tabs: List<TabsResponse.Data.Tab?>?,
    pagerState: PagerState,
    vm: OffersViewModel,
    navigateToDetail: () -> Unit
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            count = tabs?.size ?: 1
        ) { index ->

            vm.selectedTab.value = tabs?.get(index)!!

            val lazyOutlets = vm.offers.collectAsLazyPagingItems()

            LazyColumn {

                items(lazyOutlets) { outlet ->
                    OutletItem(outlet, navigateToDetail = navigateToDetail)
                }

                applyPagination(lazyOutlets)
            }
        }
    }
}

class OutletProvider : PreviewParameterProvider<OffersResponse.Data.Outlet> {
    override val values = sequenceOf(OffersResponse.Data.Outlet())
}

@Composable
@Preview
fun OutletItem(
    @PreviewParameter(OutletProvider::class) outlet: OffersResponse.Data.Outlet?,
    navigateToDetail: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .clickable {
                navigateToDetail()
            }
            .drawBehind {
                val strokeWidth = Stroke.DefaultMiter
                val x = size.width - strokeWidth
                drawLine(
                    color = Color.LightGray,
                    start = Offset(20f, 0f), //(0,0) at top-left point of the box
                    end = Offset(x - 20, 0f), //top-right point of the box
                    strokeWidth = strokeWidth
                )
            },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .height(75.dp)
                    .width(75.dp),
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(12.dp)
            ) {
                AsyncImage(model = outlet?.merchantLogoUrl, contentDescription = "")
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = outlet?.merchantName ?: "")
                Text(text = outlet?.name ?: "", modifier = Modifier.padding(vertical = 6.dp))
                Text(text = outlet?.humanLocation ?: "")
            }
        }

    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OffersScreenPreview() {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(TabsResponse.Data.Tab("All Offers"))
    val pagerState = rememberPagerState(initialPage = 0)
    Surface(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs, pagerState, coroutineScope) {

        }
        //Pager(tabs = tabs, pagerState = pagerState)
    }
}
