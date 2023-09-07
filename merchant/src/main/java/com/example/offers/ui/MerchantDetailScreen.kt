package com.example.offers.ui

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.rememberMotionLayoutState
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.vm.CommonViewModel
import com.example.offers.vm.MerchantDetailViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp

@OptIn(ExperimentalMotionApi::class)
@SuppressLint("InternalInsetResource", "DiscouragedApi")
@Composable
fun MerchantDetailScreen(
    vm: CommonViewModel,
    detailVM: MerchantDetailViewModel = getViewModel()
) {
    val maxPx = with(LocalDensity.current) { EXPANDED_TOP_BAR_HEIGHT.roundToPx().toFloat() }
    val minPx = with(LocalDensity.current) { COLLAPSED_TOP_BAR_HEIGHT.roundToPx().toFloat() }
    val toolbarHeight = remember { mutableStateOf(maxPx) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                Log.d("TAG", "available: $available")
                val height = toolbarHeight.value

                if (height + available.y > maxPx) {
                    toolbarHeight.value = maxPx
                    return Offset(0f, maxPx - height)
                }

                if (height + available.y < minPx) {
                    toolbarHeight.value = minPx
                    return Offset(0f, minPx - height)
                }

                toolbarHeight.value += available.y
                return Offset(0f, available.y)
            }

        }
    }
    val progress = 1 - (toolbarHeight.value - minPx) / (maxPx - minPx)

    Log.d("TAG", "progress: $progress")
    Log.d("TAG", "maxPx: $maxPx")
    Log.d("TAG", "minPx: $minPx")


    val lazySections =
        detailVM.merchantDetailResponse.collectAsStateLifecycleAware().value

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        val motionScene = remember {
            context.resources
                .openRawResource(com.example.base.R.raw.motion_scene)
                .readBytes()
                .decodeToString()
        }

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            motionLayoutState = rememberMotionLayoutState(),
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = com.example.base.R.drawable.demoimage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .layoutId("headerImage")
            )

            val properties = motionProperties("toolbar")

            Box(
                modifier = Modifier
//                    .nestedScroll(nestedScrollConnection)
                    .fillMaxHeight()
                    .background(Color.White)
                    .layoutId("contentBg")
            ) {
                Text(
                    text = "You will fall in love with this fresh Strawberry Cake made with a homemade strawberry reduction.\n\n" +
                            " It's moist, tender, and the most beautiful shade of pink!\n\n" +
                            "This recipe combines layers of strawberry flavored cake with strawberry flavored cream cheese frosting, so you get fresh strawberry flavors with every bite.\n\n" +
                            "This made from scratch with strawberry cake recipe is such a delightful and pretty cake. Packed with strawberry flavors and a gorgeous shade of pink, this cake is perfect for any occasion.\n\n" +
                            "No box mix or Jell-O in this cake! Made with real strawberries, this cake will steal the show with its light and tender crumb\n\n" +
                            "Filled and coated in a wonderful strawberry cream cheese frosting, this cake has strawberry flavoring through and through.\n\n" +
                            "Try making my strawberry shortcake recipe, strawberry pie recipe, strawberry chocolate cake recipe if you have extra strawberries on hand.\n" +
                            "Or, try my red velvet cake recipe or vanilla cake recipe if you want another easy cake recipe. \n" +
                            "You can leave the strawberry cake at room temperature for up to 5 days if you keep it covered. You can also store the cake in the fridge.\n" +
                            "\nYou can freeze fully frosted or unfrosted cake for up to 2 months. Wrap the cake tightly in a layer of plastic wrap and then tin foil before freezing.\n\n" +
                            "Let the cake thaw overnight in the refrigerator, then bring to room temperature before serving.You will fall in love with this fresh Strawberry Cake made with a homemade strawberry reduction.\\n\\n\" +\n" +
                            "                            \" It's moist, tender, and the most beautiful shade of pink!\\n\\n\" +\n" +
                            "                            \"This recipe combines layers of strawberry flavored cake with strawberry flavored cream cheese frosting, so you get fresh strawberry flavors with every bite.\\n\\n\" +\n" +
                            "                            \"This made from scratch with strawberry cake recipe is such a delightful and pretty cake. Packed with strawberry flavors and a gorgeous shade of pink, this cake is perfect for any occasion.\\n\\n\" +\n" +
                            "                            \"No box mix or Jell-O in this cake! Made with real strawberries, this cake will steal the show with its light and tender crumb\\n\\n\" +\n" +
                            "                            \"Filled and coated in a wonderful strawberry cream cheese frosting, this cake has strawberry flavoring through and through.\\n\\n\" +\n" +
                            "                            \"Try making my strawberry shortcake recipe, strawberry pie recipe, strawberry chocolate cake recipe if you have extra strawberries on hand.\\n\" +\n" +
                            "                            \"Or, try my red velvet cake recipe or vanilla cake recipe if you want another easy cake recipe. \\n\" +\n" +
                            "                            \"You can leave the strawberry cake at room temperature for up to 5 days if you keep it covered. You can also store the cake in the fridge.\\n\" +\n" +
                            "                            \"\\nYou can freeze fully frosted or unfrosted cake for up to 2 months. Wrap the cake tightly in a layer of plastic wrap and then tin foil before freezing.\\n\\n\" +\n" +
                            "                            \"Let the cake thaw overnight in the refrigerator, then bring to room temperature before serving.",
                    modifier = Modifier.fillMaxHeight()
                        .layoutId("text")
                        .padding(horizontal = 16.dp),
                    fontSize = 14.sp,
                )

//                LazyColumn {
//                    items(DEFAULT_BOOKS) {
//                        Book(modifier = Modifier, it)
//                    }
//                }
            }

            TopAppBar(
                backgroundColor = properties.value.color("background"), modifier = Modifier
                    .layoutId("toolbar")
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.layoutId("ivBack"),
                        painter = painterResource(com.example.base.R.drawable.ic_back),
                        contentDescription = ""
                    )

                    Image(
                        modifier = Modifier.layoutId("ivFav"),
                        painter = painterResource(com.example.base.R.drawable.ic_favorite),
                        contentDescription = ""
                    )
                }
            }


        }

    }

}

@OptIn(ExperimentalMotionApi::class)
@Composable
@Preview
private fun ScreenPreview() {
    val maxPx = with(LocalDensity.current) { EXPANDED_TOP_BAR_HEIGHT.roundToPx().toFloat() }
    val minPx = with(LocalDensity.current) { COLLAPSED_TOP_BAR_HEIGHT.roundToPx().toFloat() }
    val toolbarHeight = remember { mutableStateOf(maxPx) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val height = toolbarHeight.value

                if (height + available.y > maxPx) {
                    toolbarHeight.value = maxPx
                    return Offset(0f, maxPx - height)
                }

                if (height + available.y < minPx) {
                    toolbarHeight.value = minPx
                    return Offset(0f, minPx - height)
                }

                toolbarHeight.value += available.y
                return Offset(0f, available.y)
            }

        }
    }
    val progress = 1 - (toolbarHeight.value - minPx) / (maxPx - minPx)

    Surface(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        val motionScene = remember {
            context.resources
                .openRawResource(com.example.base.R.raw.motion_scene)
                .readBytes()
                .decodeToString()
        }

        MotionLayout(
            progress = progress,
            motionScene = MotionScene(content = motionScene),
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = com.example.base.R.drawable.demoimage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .layoutId("headerImage")
            )

            Row(
                modifier = Modifier
                    .layoutId("toolbar")
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier.layoutId("ivBack"),
                    painter = painterResource(com.example.base.R.drawable.ic_back),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier.layoutId("ivFav"),
                    painter = painterResource(com.example.base.R.drawable.ic_favorite),
                    contentDescription = ""
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .nestedScroll(nestedScrollConnection)
                    .background(Color.White)
                    .layoutId("contentBg")
            ) {

                LazyColumn {
                    items(DEFAULT_BOOKS) {
                        Book(modifier = Modifier, it)
                    }
                }
            }


        }

    }
}

@Composable
private fun AboutSection() {
    Column(modifier = Modifier.fillMaxHeight()) {
        Text("Abu dhabi")
        Text("Abu dhabi")
        Text("Abu dhabi")
        Text("Abu dhabi")
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
            Text(model.author)
            Text(model.title)
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
    BookModel(title = "Night crawling", author = "Leila Mottley", pageCount = 387),
    BookModel(title = "The Diamond Eye", author = "Kate Quinn", pageCount = 435),
    BookModel(title = "Leviathan Falls", author = "James S. A. Corey", pageCount = 528),
    BookModel(title = "Stolen Focus", author = "Johann Hari", pageCount = 357),
    BookModel(title = "Stolen Focus", author = "Johann Hari", pageCount = 357),
    BookModel(title = "Stolen Focus", author = "Johann Hari", pageCount = 357),
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

