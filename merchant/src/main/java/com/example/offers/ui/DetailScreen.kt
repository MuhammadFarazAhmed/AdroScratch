package com.example.offers.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.adro.vm.CommonViewModel
import com.example.domain.models.HomeResponse
import com.example.offers.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.MutableStateFlow

val COLLAPSED_TOP_BAR_HEIGHT = 85.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp

@SuppressLint("InternalInsetResource")
@Preview
@Composable
fun MerchantDetailScreen(vm: CommonViewModel) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        // make activity fit system window false for transparent status bar
        vm.makeStatusBarTranslucent.value = false

        onDispose {
            vm.makeStatusBarTranslucent.value = true
            systemUiController.setStatusBarColor(
                color = Color.Black,
                darkIcons = false
            )
        }
    }

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) {

        val listState = rememberLazyListState()

        val overlapHeightPx = with(LocalDensity.current) {
            val context = LocalContext.current
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx() - context.resources.getDimensionPixelSize(
                resourceId
            )
        }

        val isCollapsed: Boolean by remember {
            derivedStateOf {
                val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
                isFirstItemHidden || listState.firstVisibleItemIndex > 0
            }
        }

        if (isCollapsed) {
            systemUiController.setSystemBarsColor(
                color = Color.Black,
                darkIcons = false
            )
        } else {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = false
            )
        }

        Box {
            CollapsedTopBar(
                modifier = Modifier
                    .zIndex(2f),
                isCollapsed = isCollapsed
            )
            LazyColumn(state = listState) {
                item { ExpandedTopBar() }
                items(items = DEFAULT_BOOKS) { book ->
                    Book(model = book)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }

    }
}

@Composable
fun Book(modifier: Modifier = Modifier, model: BookModel) =
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(model.title)
            Text(model.author)
            androidx.compose.material.Text("${model.pageCount} pages")
        }
    }

data class BookModel(val title: String, val author: String, val pageCount: Int)

val DEFAULT_BOOKS = listOf(
    BookModel(
        title = "Tomorrow and Tomorrow and Tomorrow",
        author = "Gabrielle Zevin",
        pageCount = 416
    ),
    BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
    BookModel(title = "Fairy Tale", author = "Stephen King", pageCount = 500),
    BookModel(title = "Sea of Tranquility", author = "Emily St. John Mandel", pageCount = 272),
    BookModel(title = "Nightcrawling", author = "Leila Mottley", pageCount = 387),
    BookModel(title = "The Diamond Eye", author = "Kate Quinn", pageCount = 435),
    BookModel(title = "Leviathan Falls", author = "James S. A. Corey", pageCount = 528),
    BookModel(title = "Stolen Focus", author = "Johann Hari", pageCount = 357),
)

@Composable
private fun CollapsedTopBar(modifier: Modifier = Modifier, isCollapsed: Boolean) {
    val color: Color by animateColorAsState(
        if (isCollapsed) Color.Black else Color.Transparent, label = ""
    )
    Box(
        modifier = modifier
            .background(color)
            .fillMaxWidth()
            .statusBarsPadding()
            .height(COLLAPSED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = modifier.size(85.dp),
                model = com.example.base.R.drawable.ic_back,
                contentDescription = ""
            )
            AsyncImage(
                modifier = modifier.size(85.dp),
                model = com.example.base.R.drawable.ic_favorite,
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun ExpandedTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(com.example.base.R.drawable.demoimage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
//        Text(
//            modifier = Modifier.padding(16.dp),
//            text = "Library",
//            color = MaterialTheme.colors.onPrimary,
//            style = MaterialTheme.typography.h3,
//        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Header(modifier: Modifier) {
    Box(
        modifier = modifier
            .wrapContentHeight(),
        contentAlignment = Alignment.TopCenter
    ) {


    }
}
