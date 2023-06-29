package com.example.offers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.CommonUtilsExtension.applyPagination
import com.example.adro.models.OffersResponse
import com.example.offers.vm.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
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
        val debounceDuration = 800L // Debounce duration in milliseconds
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
                text = text,
                onCrossClicked = {
                    if (text.isNotEmpty())
                        Icon(Icons.Default.Close, contentDescription = "",
                            modifier = Modifier.clickable { vm.query.value = "" })
                },
                onQueryChange = { queryText -> vm.query.value = queryText }
            )

            OutletResults(lazyOutlets, navigateToDetail)

        }
    }
}


@Composable
private fun Header(
    text: String,
    onCrossClicked: @Composable () -> Unit,
    onQueryChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {

        Toolbar()

        SearchBar(text, { onCrossClicked() }, { onQueryChange(it) })

    }
}

@Composable
private fun Toolbar() {

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {

        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .size(55.dp)
                .padding(12.dp)
                .clickable {
                }
        )

        Text(
            text = "Search",
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )

    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBar(
    text: String,
    trailingIconClicked: @Composable () -> Unit,
    onQueryChange: (String) -> Unit
) {

    SearchBar(
        query = text,
        colors = SearchBarDefaults.colors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp),
        active = false,
        onSearch = {
        },
        onActiveChange = {
        },
        placeholder = {
            Text(text = "What are you looking for?")
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "")
        },
        trailingIcon = {
            trailingIconClicked()
        },
        onQueryChange = {
            onQueryChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 22.dp)
    ) {}
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

            applyPagination(lazyOutlets)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun SearchViewPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchBar(query = "mak",
                onQueryChange = {},
                onSearch = {},
                active = true,
                onActiveChange = {}) {
            }
        }
    }
}