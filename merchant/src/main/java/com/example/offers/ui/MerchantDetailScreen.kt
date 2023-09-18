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
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutState
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.rememberMotionLayoutState
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.vm.CommonViewModel
import com.example.domain.models.MerchantDetailModel
import com.example.offers.vm.MerchantDetailViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp + 24.dp

@OptIn(ExperimentalMotionApi::class)
@SuppressLint("InternalInsetResource", "DiscouragedApi")
@Composable
fun MerchantDetailScreen(
    vm: CommonViewModel,
    detailVM: MerchantDetailViewModel = getViewModel()
) {

    val motionState = rememberMotionLayoutState()
    val lazySections =
        detailVM.merchantDetailResponse.collectAsStateLifecycleAware().value


    ChangeStatusBar(isCollapsed = motionState.currentProgress == 1f, vm = vm)

    MainLayout(lazySections, motionState)

}

@OptIn(ExperimentalMotionApi::class, ExperimentalLayoutApi::class)
@Composable
private fun MainLayout(
    lazySections: List<MerchantDetailModel.Data.Detail>?,
    motionState: MotionLayoutState
) {


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
            motionLayoutState = motionState,
            modifier = Modifier
                .fillMaxSize()
        ) {


            Image(
                painter = painterResource(id = com.example.base.R.drawable.demoimage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("collapsing_image")
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
                alignment = BiasAlignment(0f, 1f - ((1f - motionState.currentProgress) * 0.50f))
            )

            val property = motionProperties("toolbar")
            Row(
                modifier = Modifier
                    .layoutId("toolbar")
                    .background(property.value.color("background"))
            ) {}

            val titleProperty = motionProperties("title")
            Text(
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false,
                    ),
                ),
                modifier = Modifier
                    .zIndex(1f)
                    .layoutId("title"),
                textAlign = TextAlign.Center,
                text = "Title",
                color = titleProperty.value.color("textColor"),
                fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra)),
                fontWeight = FontWeight(500),
                fontSize = titleProperty.value.fontSize("textSize"),
                // style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
            )

            Text(
                modifier = Modifier.layoutId("description"),
                text = "description",
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )

            Text(
                modifier = Modifier.layoutId("location"),
                text = "location",
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )

            FlowRow(modifier = Modifier.layoutId("chips")) {
                for (item in 1..10) {
                    Text(text = buildAnnotatedString {
                        withStyle(ParagraphStyle()) {
                            append(" \u2022 ")
                            append("$item")
                        }
                    }
                    )
                }
            }

            Divider(
                modifier = Modifier.layoutId("divider"),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Image(
                modifier = Modifier.layoutId("ivBack"),
                painter = painterResource(com.example.base.R.drawable.ic_back),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Image(
                modifier = Modifier.layoutId("ivFav"),
                painter = painterResource(com.example.base.R.drawable.ic_favorite),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        androidx.compose.material3.MaterialTheme.colorScheme.surface
                    )
                    .layoutId("contentBg")
            ) {
                Column(modifier = Modifier) {
                    lazySections?.forEach {
                        when (it.sectionIdentifier) {
                            "header" -> {}
                            "available_offers" -> {
                                lazySections.find { it.sectionIdentifier == "available_offers" }?.outlets?.forEach {
                                    Column {
                                        Text(text = "Hello")
                                    }
                                }
                            }

                            "about" -> {}
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
@Preview
private fun ScreenPreview() {
    MainLayout(null, rememberMotionLayoutState())
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

