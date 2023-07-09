package com.example.home.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.CommonFlowExtensions.findActivity
import com.example.adro.common.CommonUtilsExtension.applyPagination
import com.example.adro.common.HexToJetpackColor
import com.example.adro.components.SwipeToRefreshContainer
import com.example.adro.models.HomeResponse
import com.example.adro.ui.ProgressDialog
import com.example.base.R
import com.example.home.ui.HomeSections.CATEGORIES
import com.example.home.ui.HomeSections.EXCLUSIVE_OFFERS
import com.example.home.ui.HomeSections.GUEST_USER
import com.example.home.ui.HomeSections.MAIN_CAROUSAL
import com.example.home.ui.HomeSections.RECOMMENDED_OFFERS
import com.example.home.vm.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.util.Locale

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun HomeScreenPreview() {

    val pagerState = rememberPagerState()
    val exclusivePagerState = rememberPagerState()
    val recommendedPagerState = rememberPagerState()
    val section = HomeResponse.Data.Section("")

    Column {

        MainCarousal(pagerState, section)

        Categories(section) {

        }

        ExclusiveItem(exclusivePagerState, section)

        RecommendedItem(recommendedPagerState, section)

    }

}

enum class HomeSections(val value: String) {
    MAIN_CAROUSAL("main_carousal"),
    GUEST_USER("guest_user"),
    CATEGORIES("categories"),
    EXCLUSIVE_OFFERS("exclusive_offers"),
    RECOMMENDED_OFFERS("recommended_offers"),
}

@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navigateToAuth: () -> Unit,
    navigateToOffers: () -> Unit,
    isApiLoading: (loading: Boolean) -> Unit,
    handleDeepLinks: () -> Unit,
    vm: HomeViewModel = getViewModel()
) {

    val pagerState = rememberPagerState()
    val exclusivePagerState = rememberPagerState()
    val recommendedPagerState = rememberPagerState()

    val context = LocalContext.current

    val homeSection = vm.sections.collectAsLazyPagingItems()
    val isRefreshing by vm.isRefreshing.collectAsStateLifecycleAware()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { homeSection.refresh() })


    SwipeToRefreshContainer(
        pullRefreshState = pullRefreshState,
        isRefreshing = isRefreshing,
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(),
        content = {
            LazyColumn {

                items(
                    count = homeSection.itemCount,
                    key = homeSection.itemKey(),
                    contentType = homeSection.itemContentType()
                ) { index ->
                    val section = homeSection[index]

                    when (section?.sectionIdentifier) {

                        MAIN_CAROUSAL.value -> MainCarousal(pagerState, section)
                        GUEST_USER.value -> LoginView(section, navigateToAuth, vm)

                        CATEGORIES.value -> Categories(section) { deeplink ->
                            context.startActivity(
                                Intent(Intent.ACTION_VIEW, Uri.parse(deeplink))
                            )
                        }

                        EXCLUSIVE_OFFERS.value -> ExclusiveItem(exclusivePagerState, section)
                        RECOMMENDED_OFFERS.value -> RecommendedItem(
                            recommendedPagerState,
                            section
                        )
                    }
                }
                applyPagination(homeSection)
            }
        }
    )

    //show progress
    if (isRefreshing) {
        ProgressDialog(Color.White, alpha = 1.0)
    }

    //callback for main activity to hide bottom nav rail
    isApiLoading(isRefreshing)

}

class SampleUserProvider : PreviewParameterProvider<HomeResponse.Data.Section> {
    override val values = sequenceOf(HomeResponse.Data.Section())
}

@Composable
fun LoginView(
    @PreviewParameter(SampleUserProvider::class) section: HomeResponse.Data.Section?,
    navigateToAuth: () -> Unit, vm: HomeViewModel
) {

    Column {
        Box(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = section?.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(), contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color.Black.copy(alpha = 0.7f),
                                Color.Black.copy(alpha = 0f)
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    style = MaterialTheme.typography.body2,
                    text = section?.title ?: "",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    style = MaterialTheme.typography.h1,
                    text = section?.subTitle ?: "",
                    color = Color.White,
                    letterSpacing = 1.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth(.6f)
                        .padding(bottom = 12.dp)
                )

                Button(
                    onClick = { },
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    modifier = Modifier
                        .height(34.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = HexToJetpackColor.getColor(
                            section?.buttonBgColor ?: "acccbc"
                        )
                    )
                ) {
                    Text(
                        text = section?.buttonTitle ?: "",
                        color = Color.White,
                        style = MaterialTheme.typography.body2,
                        fontSize = 12.sp
                    )
                }

            }
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
            border = BorderStroke(2.dp, HexToJetpackColor.getColor("acccbc")),
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
                .wrapContentHeight()
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .width(IntrinsicSize.Max)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_limited_offer_1),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .height(40.dp)
                            .width(40.dp)
                    )
                    Text(
                        text = "Login to enjoy discounts and offers",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(.7f)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.body1.copy(lineHeight = 21.sp)
                    )

                    Button(contentPadding = PaddingValues(horizontal = 4.dp),
                        modifier = Modifier
                            .height(34.dp)
                            .align(Alignment.CenterVertically)
                            .width(80.dp), onClick = {
                            navigateToAuth()
                        }) {
                        Text(
                            text = stringResource(R.string.login),
                            fontSize = 14.sp,
                            fontWeight = Bold,
                            style = MaterialTheme.typography.body2
                        )
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(HexToJetpackColor.getColor("acccbc"))

                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 8.dp, horizontal = 2.dp),
                        text = "Thrive Club app is exclusively for Abu Dhabi Golden Visa holders",
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainCarousal(
    pagerState: PagerState,
    @PreviewParameter(SampleUserProvider::class) section: HomeResponse.Data.Section
) {

    HorizontalPager(
        count = section.sectionItems.size,
        state = pagerState,
        modifier = Modifier.height(250.dp),
        contentPadding = PaddingValues(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 24.dp),
        itemSpacing = 8.dp
    ) { index ->

        val item = section.sectionItems[index]

        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {

            AsyncImage(
                model = item.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color.Black.copy(alpha = 0.7f),
                                Color.Black.copy(alpha = 0f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = item.title ?: "",
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth(.8f),
                    fontWeight = SemiBold
                )

                Button(
                    onClick = { },
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    modifier = Modifier
                        .height(34.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = HexToJetpackColor.getColor(
                            item.buttonBgColor
                        )
                    )
                ) {
                    Text(text = item.buttonTitle, color = Color.White, fontSize = 12.sp)
                }

            }
        }
    }
}

@Composable
fun Categories(
    @PreviewParameter(SampleUserProvider::class) section: HomeResponse.Data.Section,
    handleDeeplink: (deepLink: String) -> Unit
) {
    Column {

        Text(
            text = section.title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 16.dp)
        )

        LazyRow(
            modifier = Modifier
                .wrapContentHeight()
                .padding(vertical = 16.dp)
                .padding(start = 16.dp)
        ) {

            items(section.sectionItems) { item ->
                Card(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(4.dp)
                        .width(115.dp)
                        .clickable {
                            handleDeeplink(item.deeplink)
                        },
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(109.dp)
                        ) {

                            AsyncImage(
                                model = item.imageUrl,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                contentDescription = ""
                            )
                        }

                        Text(
                            text = item.title ?: "",
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 12.sp
                        )
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ExclusiveItem(
    pagerState: PagerState,
    @PreviewParameter(SampleUserProvider::class) section: HomeResponse.Data.Section
) {

    Column(modifier = Modifier.wrapContentHeight()) {

        Text(
            text = section.title,
            style = MaterialTheme.typography.h1,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 0.dp)
        )

        HorizontalPager(
            count = section.sectionItems.size,
            state = pagerState,
            modifier = Modifier
                .height(175.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            itemSpacing = 8.dp
        ) { index ->

            val item = section.sectionItems[index]

            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {

                AsyncImage(
                    model = item.imageUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color.Black.copy(alpha = 0.7f),
                                    Color.Black.copy(alpha = 0f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = item.title ?: "",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = SemiBold,
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    Text(
                        text = item.subtitle ?: "",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = SemiBold,
                        modifier = Modifier.padding(top = 12.dp)
                    )

                }

            }
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecommendedItem(
    pagerState: PagerState,
    @PreviewParameter(SampleUserProvider::class) section: HomeResponse.Data.Section
) {

    Column(modifier = Modifier.wrapContentHeight()) {

        Text(
            text = section.title,
            style = MaterialTheme.typography.h1,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 0.dp)
        )

        HorizontalPager(
            count = section.sectionItems.size,
            state = pagerState,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            itemSpacing = 8.dp
        ) { index ->

            val item = section.sectionItems[index]

            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {

                AsyncImage(
                    model = item.imageUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color.Black.copy(alpha = 0.7f),
                                    Color.Black.copy(alpha = 0f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = item.title ?: "",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = SemiBold,
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    Text(
                        text = item.subtitle ?: "",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = SemiBold,
                        modifier = Modifier.padding(top = 12.dp)
                    )

                }

            }

        }
    }
}
