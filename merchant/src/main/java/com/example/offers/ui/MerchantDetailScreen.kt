package com.example.offers.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.vm.CommonViewModel
import com.example.offers.vm.MerchantDetailViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp

@SuppressLint("InternalInsetResource", "DiscouragedApi")
@Composable
fun MerchantDetailScreen(
    vm: CommonViewModel,
    detailVM: MerchantDetailViewModel = getViewModel()
) {

    val listState = rememberLazyListState()

    val overlapHeightPx = with(LocalDensity.current) {
        val context = LocalContext.current
        val resourceId =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx() - context.resources.getDimensionPixelSize(
            resourceId
        ) - 12.dp.toPx()
    }

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }

    ChangeStatusBar(vm = vm, isCollapsed = isCollapsed)


    val lazySections =
        detailVM.merchantDetailResponse.collectAsStateLifecycleAware().value

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Box {
            CollapsedTopBar(
                modifier = Modifier
                    .zIndex(2f),
                isCollapsed = isCollapsed
            )
            LazyColumn(state = listState) {
                item { ExpandedTopBar() }
                item { AboutSection() }
                items(DEFAULT_BOOKS) {
                    Book(modifier = Modifier, it)
                }
            }
        }
    }

}


@Composable
@Preview
private fun CollapsedToolbarStates() {
    Column {
        CollapsedTopBar(
            modifier = Modifier,
            isCollapsed = true
        )

        Spacer(modifier = Modifier.padding(16.dp))

        CollapsedTopBar(
            modifier = Modifier,
            isCollapsed = false
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview
private fun ExpandedTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Image(
                modifier = Modifier.height(EXPANDED_TOP_BAR_HEIGHT),
                painter = painterResource(com.example.base.R.drawable.demoimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Text(
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(700),
                    color = Color.White,
                ),
                modifier = Modifier
                    .wrapContentSize(),
                text = "Title",
                color = MaterialTheme.colors.onSurface
            )

            Text("Abu dhabi", color = MaterialTheme.colors.onSurface)
            Text("Location: Dusit Thani Abu Dhabi ", color = MaterialTheme.colors.onSurface)
            FlowRow {
                for (i in 1..10) {
                    Text(buildAnnotatedString {
                        withStyle(ParagraphStyle()) {
                            append(" \u2022")
                            append("Dessert ")
                        }
                    })
                }
            }
        }


    }
}

@Composable
@Preview
private fun AboutSection() {
    Column {
        Text("Abu dhabi")
        Text("Abu dhabi")
        Text("Abu dhabi")
        Text("Abu dhabi")
    }
}

@SuppressLint("DiscouragedApi", "InternalInsetResource")
@Composable
@Preview
private fun ScreenPreview() {

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

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Box {
            CollapsedTopBar(
                modifier = Modifier
                    .zIndex(2f),
                isCollapsed = isCollapsed
            )
            LazyColumn(state = listState) {
                item { ExpandedTopBar() }
                items(DEFAULT_BOOKS) {
                    Book(modifier = Modifier, it)
                }
            }
        }
    }
}


@Composable
private fun ChangeStatusBar(isCollapsed: Boolean, vm: CommonViewModel) {

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
}

@Composable
fun Book(modifier: Modifier = Modifier, model: BookModel) =
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(model.author ?: "")
            Text(model.title ?: "")
            androidx.compose.material.Text("${model.author} pages")
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
            .padding(vertical = 6.dp)
            .height(COLLAPSED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = com.example.base.R.drawable.ic_back),
                    contentDescription = "", contentScale = ContentScale.Crop
                )

                AnimatedVisibility(visible = isCollapsed, enter = fadeIn(), exit = fadeOut()) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight(700),
                                color = Color.White,
                            ),
                            modifier = modifier
                                .wrapContentSize(),
                            text = "Title",
                            color = MaterialTheme.colors.surface
                        )
                    }
                }
            }

            Column {
                Image(
                    painter = painterResource(id = com.example.base.R.drawable.ic_favorite),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

