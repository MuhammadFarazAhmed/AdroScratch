package com.example.adro.ui.offers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adro.ui.offers.common.PagerExtension.pagerTabIndicatorOffset
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@Composable
fun OffersScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        CombinedTab()
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalPagerApi::class)
@Composable
fun CombinedTab() {
    val tabData = listOf(
            "MUSIC" to Icons.Filled.Home,
            "MARKET" to Icons.Filled.ShoppingCart,
            "FILMS" to Icons.Filled.AccountBox,
            "BOOKS" to Icons.Filled.Settings,
                        )
    val pagerState = rememberPagerState(initialPage = 0)
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column {
        ScrollableTabRow(edgePadding = 0.dp, selectedTabIndex = tabIndex, indicator = { tabPositions ->
            TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, tabPositions))
        }) {
            tabData.forEachIndexed { index, pair ->
                Tab(selected = tabIndex == index,
                        modifier = Modifier.background(Color.Black),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(text = pair.first, color = Color.Red)
                        })
            }
        }
        HorizontalPager(state = pagerState,
                modifier = Modifier.weight(1f),
                count = tabData.size) { index ->
            Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                        text = tabData[index].first,
                    )
            }
        }
    }
}

@Preview
@Composable
fun OffersScreenPreview() {
    OffersScreen()
}
