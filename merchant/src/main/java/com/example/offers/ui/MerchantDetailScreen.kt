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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
import com.example.domain.models.MerchantDetailModel
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

    val lazySections =
        detailVM.merchantDetailResponse.collectAsStateLifecycleAware().value

    MainLayout(lazySections)

}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun MainLayout(lazySections: List<MerchantDetailModel.Data.Detail>?) {

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
        val motionState = rememberMotionLayoutState()
        val corners = 10f - ((motionState.currentProgress * 10)).coerceAtMost(10f)

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            motionLayoutState = motionState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {

            Image(
                painter = painterResource(id = com.example.base.R.drawable.demoimage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .layoutId("headerImage")
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = corners, topEnd = corners)
                    )
                    .layoutId("contentBg")
            )

            Text(
                text = "Fresh Strawberry Cake", fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold, modifier = Modifier
                    .layoutId("title")
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Divider(
                Modifier
                    .layoutId("titleDivider")
                    .fillMaxWidth()
                    .padding(horizontal = 34.dp)
            )

            Text(
                text = "by John Kanell", fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray, fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .layoutId("subTitle")
                    .fillMaxWidth()
                    .padding(6.dp)
            )

            Divider(
                Modifier
                    .layoutId("subTitleDivider")
                    .fillMaxWidth()
                    .padding(horizontal = 34.dp)
            )

            Text(
                modifier = Modifier
                    .layoutId("date")
                    .fillMaxWidth()
                    .padding(6.dp),
                text = "September, 2022", fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

            val properties = motionProperties("actions")

            Row(
                modifier = Modifier
                    .layoutId("actions")
                    .background(properties.value.color("background")),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                IconButton(onClick = { }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Share, contentDescription = "", tint = Color.White)
                        Text(text = "SHARE", color = Color.White, fontSize = 12.sp)
                    }
                }

                IconButton(onClick = { }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Outlined.ThumbUp, contentDescription = "", tint = Color.White)
                        Text(text = "LIKE", color = Color.White, fontSize = 12.sp)
                    }
                }

                IconButton(onClick = { }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Outlined.Star, contentDescription = "", tint = Color.White)
                        Text(text = "SAVE", color = Color.White, fontSize = 12.sp)
                    }
                }
            }

            val books = lazySections?.map { BookModel(it.sectionTitle ?: "", "", 0) }
            Column(modifier = Modifier) {
                books?.forEach {
                    Book(modifier = Modifier, it)
                } ?: DEFAULT_BOOKS.forEach {
                    Book(modifier = Modifier, it)
                }
            }

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.layoutId("menu"), contentPadding = PaddingValues(4.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 0.dp),
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }


        }

    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
@Preview
private fun ScreenPreview() {
    MainLayout(null)
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

