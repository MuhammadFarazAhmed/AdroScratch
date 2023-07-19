package com.example.offers.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.adro.PagerExtension.pagerTabIndicatorOffset
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.CommonUtilsExtension.applyPagination
import com.example.adro.components.Header
import com.example.adro.components.SwipeToRefreshContainer
import com.example.adro.models.OffersResponse
import com.example.adro.models.TabsResponse
import com.example.adro.ui.ProgressDialog
import com.example.offers.vm.OffersViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun OffersScreen(
    navigateToDetail: () -> Unit,
    params: HashMap<String, String> = hashMapOf(),
    vm: OffersViewModel = getViewModel()
) {

    Log.d("TAG", "OffersScreen: $params")

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val tabs by vm.tabs.collectAsStateLifecycleAware()

    val searchQuery = vm.query.collectAsState().value
    val lazyOutlets = vm.offers.collectAsLazyPagingItems()

    val isRefreshing by vm.isRefreshing.collectAsStateLifecycleAware()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { lazyOutlets.refresh() })

    val showBackButton = remember { mutableStateOf(params["category"] != null) }.value

    LaunchedEffect(key1 = params) {
        coroutineScope.launch {
            if (params.isNotEmpty()) {
                vm.deepLinkParams.value = params
            }
        }
    }


    SwipeToRefreshContainer(
        pullRefreshState = pullRefreshState,
        isRefreshing = isRefreshing,
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(),
        content = {
            Surface(modifier = Modifier.fillMaxSize()) {

                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        if (tabs.isNotEmpty())
                            vm.selectedTab.value = tabs[page]
                    }
                }

                Column {

                    Header(
                        toolbarTitle = params["category"] ?: "Offers",
                        searchText = searchQuery,
                        isBackIconShown = showBackButton,
                        onQueryChange = { newQuery -> vm.query.value = newQuery },
                        onCrossClicked = { if (searchQuery.isNotEmpty()) vm.query.value = "" }
                    )

                    Column {

                        Tabs(tabs, pagerState, coroutineScope)

                        Pager(tabs, pagerState, lazyOutlets, navigateToDetail)

                        if (isRefreshing) {
                            ProgressDialog(Color.White, alpha = 1.0)
                        }
                    }

                }
            }
        }
    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(
    tabs: List<TabsResponse.Data.Tab?>?,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
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
    tabs: List<TabsResponse.Data.Tab>,
    pagerState: PagerState,
    lazyOutlets: LazyPagingItems<OffersResponse.Data.Outlet>,
    navigateToDetail: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            count = tabs.size
        ) { index ->

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {

                items(
                    count = lazyOutlets.itemCount,
                    key = lazyOutlets.itemKey(),
                    contentType = lazyOutlets.itemContentType()
                ) { index ->
                    val item = lazyOutlets[index]
                    OutletItem(item, navigateToDetail = navigateToDetail)
                    Divider(
                        color = MaterialTheme.colors.onSurface,
                        thickness = .5.dp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
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
@Preview
fun OffersScreenPreview() {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(TabsResponse.Data.Tab("All Offers"))
    val pagerState = rememberPagerState(initialPage = 0)
    Surface(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs, pagerState, coroutineScope)
    }
}
