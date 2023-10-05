package com.example.offers.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutScope
import androidx.constraintlayout.compose.MotionScene
import coil.compose.AsyncImage
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.HexToJetpackColor
import com.example.adro.vm.CommonViewModel
import com.example.domain.models.MerchantDetailModel
import com.example.offers.vm.MerchantDetailViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@SuppressLint("InternalInsetResource", "DiscouragedApi")
@Composable
fun MerchantDetailScreen(
    vm: CommonViewModel,
    detailVM: MerchantDetailViewModel = getViewModel()
) {

    val lazySections =
        detailVM.merchantDetailResponse.collectAsStateLifecycleAware().value



    ChangeStatusBar(vm)

    MainLayout(lazySections)

}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun MainLayout(
    lazySections: List<MerchantDetailModel.Data.Detail>?,
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

        val maxPx = with(LocalDensity.current) { 250.dp.roundToPx().toFloat() }
        val minPx = with(LocalDensity.current) { 50.dp.roundToPx().toFloat() }
        val toolbarHeight = remember { mutableStateOf(maxPx) }

        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val height = toolbarHeight.value;

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

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val titleProperty = motionProperties("title")
            val property = motionProperties("toolbar")

            lazySections?.let { lazyItems ->
                if (lazyItems.isNotEmpty()) {
                    MotionHeaderLayout(
                        progress = progress,
                        lazyItems[0],
                        titleProperty = titleProperty,
                        property
                    )
                    MotionListLayout(lazyItems, nestedScrollConnection)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MotionHeaderLayout(
    progress: Float = 0f,
    item: MerchantDetailModel.Data.Detail,
    titleProperty: State<MotionLayoutScope.MotionProperties>,
    property: State<MotionLayoutScope.MotionProperties>
) {

    AsyncImage(
        model = item.imageUrl,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
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
        alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.50f))
    )

    Text(
        modifier = Modifier
            .layoutId("title")
            .zIndex(10f),
        textAlign = TextAlign.Center,
        text = item.merchantName ?: "",
        color = titleProperty.value.color("textColor"),
        fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra)),
        fontWeight = FontWeight(500),
        fontSize = 22.sp,
        style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
    )

    Text(
        modifier = Modifier
            .layoutId("description"),
        text = item.locationDescription ?: "",
        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
    )

    Text(
        modifier = Modifier
            .layoutId("location"),
        text = item.locationDescription ?: "",
        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
    )


    FlowRow(modifier = Modifier.layoutId("chips")) {
        item.categoryItem?.forEach {
            Text(text = buildAnnotatedString {
                withStyle(ParagraphStyle()) {
                    append(" \u2022 ")
                    append("${it?.itemName}")
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


    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .layoutId("toolbar")
            .background(property.value.color("background"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                modifier = Modifier
                    .layoutId("ivBack")
                    .height(55.dp),
                painter = painterResource(com.example.base.R.drawable.ic_back),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Image(
                modifier = Modifier.height(55.dp),
                painter = painterResource(com.example.base.R.drawable.ic_favorite),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }


}


@Composable
private fun MotionListLayout(
    lazyItems: List<MerchantDetailModel.Data.Detail> = emptyList(),
    nestedScrollConnection: NestedScrollConnection
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
            .nestedScroll(nestedScrollConnection)
            .layoutId("contentBg")
    ) {
        LazyColumn(modifier = Modifier) {
            items(lazyItems)
            {
                when (it.sectionIdentifier) {
                    "available_offers" -> {
                        Column {

                            Text(
                                modifier = Modifier.zIndex(1f),
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra)),
                                fontWeight = FontWeight(500),
                                fontSize = 30.sp,
                                text = "Available Offers",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 21.sp,
                                    fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF3F3E44),
                                )
                            )
                        }
                        it.offers?.forEach {
                            it?.offersToDisplay?.forEach { offer ->
                                TicketShapeComposable(color = offer?.categoryColor?.let { it1 ->
                                    HexToJetpackColor.getColorWithHash(it1)
                                })

                            }
                        }
                    }

                    "about" -> {
                        val item = it
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 21.sp,
                                    fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF3F3E44),
                                ),
                                modifier = Modifier.zIndex(1f),
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra)),
                                fontWeight = FontWeight(500),
                                fontSize = 30.sp,
                                text = item.sectionTitle.toString()
                            )
                            Text(text = item.outletDescription.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TicketShapeComposable(
    modifier: Modifier = Modifier,
    color: Color? = Color(0xFFF58423)
) {

    Box(modifier = modifier
        .padding(vertical = 8.dp, horizontal = 16.dp)
        .wrapContentHeight()
        .fillMaxWidth()
        .drawBehind {
            val middleY = size.height.div(other = 2)
            val circleRadiusInPx = 10.dp.toPx()

            val roundedRect = RoundRect(
                size.toRect(),
                CornerRadius(CornerSize(10.dp).toPx(size, this))
            )
            val roundedRectPath = Path().apply { addRoundRect(roundedRect) }
            val ticketPath = Path().apply {
                reset()
                lineTo(x = 0F, y = 0F)
                lineTo(x = size.width, y = 0F)
                arcTo(
                    rect = Rect(
                        left = size.width - circleRadiusInPx,
                        top = middleY.minus(circleRadiusInPx),
                        right = size.width + circleRadiusInPx,
                        bottom = middleY.plus(circleRadiusInPx)
                    ),
                    startAngleDegrees = 270F,
                    sweepAngleDegrees = -180F,
                    forceMoveTo = false
                )
                lineTo(x = size.width, y = size.height)
                lineTo(x = 0F, y = size.height)
                arcTo(
                    rect = Rect(
                        left = 0F.minus(circleRadiusInPx),
                        top = middleY.minus(circleRadiusInPx),
                        right = 0F.plus(circleRadiusInPx),
                        bottom = middleY.plus(circleRadiusInPx)
                    ),
                    startAngleDegrees = 90F,
                    sweepAngleDegrees = -180F,
                    forceMoveTo = false
                )
                lineTo(x = 0F, y = 0F)
            }

            val path = Path.combine(
                operation = PathOperation.Intersect,
                path1 = roundedRectPath,
                path2 = ticketPath
            )

            drawPath(
                path,
                Color(0xFFFFFFFF), style = Fill, alpha = 1F
            )

            drawPath(
                path,
                Brush.linearGradient(
                    color?.let {
                        listOf(
                            color, color
                        )
                    } ?: listOf(Color(0xFFF58423))

                ), style = Stroke(6f), alpha = 1F
            )

        }
        .padding(4.dp)
    ) {

        TicketInnerUI()

    }

}

@Composable
private fun TicketInnerUI() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(IntrinsicSize.Min)
    ) {

        Image(
            modifier = Modifier
                .size(60.dp),
            painter = painterResource(id = com.example.base.R.drawable.ic_limited_offer_1),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )

        Divider(
            color = Color(0xFFF58423),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                    text = "25% OFF total bill",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 10.sp,
                        fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF282828),
                    )
                )
                Text(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFFCF1DD),
                            shape = RoundedCornerShape(CornerSize(50.dp))
                        )
                        .wrapContentSize()
                        .padding(horizontal = 6.dp),
                    text = "Reusable",
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontFamily = FontFamily(Font(com.example.base.R.font.emad_diana_extra))
                    ),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF58423),
                )

            }
            Text(modifier = Modifier.padding(start = 8.dp), text = "Dine-in")
            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = "Valid to 31 Dec 2021"
            )

        }


    }
}


@Composable
private fun ChangeStatusBar(vm: CommonViewModel) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        // make activity fit system window false for transparent status bar
        vm.makeStatusBarTranslucent.value = false
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )

        onDispose {
            vm.makeStatusBarTranslucent.value = true
            systemUiController.setStatusBarColor(
                color = Color.Black,
                darkIcons = false
            )
        }
    }
}


