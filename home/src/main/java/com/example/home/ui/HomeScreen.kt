package com.example.home.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import com.example.adro.common.CommonExtensions.collectAsStateLifecycleAware
import com.example.adro.common.CommonExtensions.rememberFlow
import com.example.base.R
import com.example.domain.models.Section
import com.example.domain.models.SectionItem
import com.example.home.vm.HomeViewModel
import com.fasterxml.jackson.databind.type.TypeBindings
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenPreview() {

    val pagerState = rememberPagerState()
    val exclusivePagerState = rememberPagerState()
    val recommendedPagerState = rememberPagerState()

    val isLoggedIn by remember {
        mutableStateOf(false)
    }

    val homeList = remember {

        mutableStateListOf(
            Section(
                "main_carousal",
                mutableListOf(
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://merchantdetail?o_id=96714&m_id=55192",
                        366,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/1+Thrive_Banners_NoText_2.8.22_Air+Balloon+ENG+342x200.jpg",
                        true,
                        1,
                        "",
                        "Fly High!"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://merchantdetail?o_id=2002&m_id=1286",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/2+Thrive_Banners_NoText_2.8.22_Beach+ENG+342x200.jpg",
                        true,
                        1,
                        "",
                        "Soak up the sun"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://merchantdetail?o_id=12529&m_id=1134",
                        368,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/3+Thrive_Banners_NoText_2.8.22_Desert+ENG+342x200.jpg",
                        true,
                        1,
                        "",
                        "Adventure is calling"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://merchantdetail?o_id=64045&m_id=37500",
                        371,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/4+Thrive_Banners_NoText_2.8.22_Family+ENG+342x200.jpg",
                        true,
                        1,
                        "",
                        "Experience more this weekend"
                    )
                ),
                1,
                "", "", ""
            ),
            Section(
                "categories",
                mutableListOf(
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Restaurants%20and%20bars",
                        366,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Food_and_drink.png",
                        true,
                        1,
                        "",
                        "Food & Drink"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Beauty & Fitness"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Attraction & Leisure"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Beauty & Fitness"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Travel"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Fashion & Retail"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Everyday Services"
                    )
                ),
                1,
                "Categories", "", ""
            ),
            Section(
                "exclusive_offers",
                mutableListOf(
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Restaurants%20and%20bars",
                        366,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Food_and_drink.png",
                        true,
                        1,
                        "",
                        "Food & Drink"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Beauty & Fitness"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Attraction & Leisure"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Beauty & Fitness"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Travel"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Fashion & Retail"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Everyday Services"
                    )
                ),
                1,
                "Exclusive offers from partners", "", ""
            ),
            Section(
                "recommended_offers",
                mutableListOf(
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Restaurants%20and%20bars",
                        366,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Food_and_drink.png",
                        true,
                        1,
                        "",
                        "Food & Drink"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Beauty & Fitness"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Attraction & Leisure"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Beauty & Fitness"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Travel"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Fashion & Retail"
                    ),
                    SectionItem(
                        "",
                        "Learn More",
                        "adoentertainer://offers?category=Body",
                        367,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                        true,
                        1,
                        "",
                        "Everyday Services"
                    )
                ),
                1,
                "Recommended offers", "", ""
            )
        )

    }

//    if (isLoggedIn) {
//        homeList.add(1, Section("login", mutableListOf(), 1, ""))
//    } else homeList.removeAt(1)


    Surface(modifier = Modifier.background(Color.White)) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(homeList) { section ->

                when (section.section_identifier) {

                    "main_carousal" -> MainCarousal(pagerState, section)

                    "login" -> LoginView(section)

                    "categories" -> Categories(section)

                    "exclusive_offers" -> ExclusiveItem(pagerState = exclusivePagerState, section)

                    "recommended_offers" -> RecommendedItem(
                        pagerState = recommendedPagerState,
                        section
                    )

                }

            }

        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {

    val vm = hiltViewModel<HomeViewModel>()

    val pagerState = rememberPagerState()
    val exclusivePagerState = rememberPagerState()
    val recommendedPagerState = rememberPagerState()

    val homeSection by vm.sections.collectAsStateLifecycleAware(initial = emptyList())

    val isLoggedIn by remember {
        mutableStateOf(true)
    }

//    if (!isLoggedIn) {
//        homeList.value.get(1) = Section("login", mutableListOf(), 1, "")
//    } else
//        homeList.removeAt(1)

    Surface(modifier = Modifier.background(Color.White)) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(homeSection) { section ->

                when (section.section_identifier) {

                    "main_carousal" -> MainCarousal(pagerState, section)

                    "guest_user" -> LoginView(section)

                    "categories" -> Categories(section)

                    "exclusive_offers" -> ExclusiveItem(
                        pagerState = exclusivePagerState,
                        section
                    )

                    "recommended_offers" -> RecommendedItem(
                        pagerState = recommendedPagerState,
                        section
                    )

                }

            }
        }
    }

}

class SampleUserProvider : PreviewParameterProvider<Section> {
    override val values = sequenceOf(Section("Jens", listOf(), 1))
}

@Composable
@Preview
fun LoginView(@PreviewParameter(SampleUserProvider::class) section: Section?) {

    Column {
        Box(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = section?.image_url,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(), contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.sweepGradient(
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
                    text = section?.sub_title ?: "",
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
                            section?.button_bg_color ?: "acccbc"
                        )
                    )
                ) {
                    Text(
                        text = section?.button_title ?: "",
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
                            .width(80.dp), onClick = { }) {
                        Text(
                            text = "Login",
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainCarousal(pagerState: PagerState, section: Section) {

    HorizontalPager(
        count = section.section_items.size,
        state = pagerState,
        modifier = Modifier.height(250.dp),
        contentPadding = PaddingValues(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 24.dp),
        itemSpacing = 8.dp
    ) { index ->

        val item = section.section_items[index]

        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {

            AsyncImage(
                model = item.image_url,
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
                    text = item.title,
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
                            "e43338"
                        )
                    )
                ) {
                    Text(text = item.button_title, color = Color.White, fontSize = 12.sp)
                }

            }
        }
    }
}

@Composable
fun Categories(section: Section) {
    Column {

        Text(
            text = section.title,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 16.dp)
        )

        LazyRow(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp)
        ) {

            items(section.section_items) { item ->
                Card(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(4.dp)
                        .width(115.dp),
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
                                model = item.image_url,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                contentDescription = ""
                            )
                        }

                        Text(
                            text = item.title,
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
fun ExclusiveItem(pagerState: PagerState, section: Section) {

    Column(modifier = Modifier.wrapContentHeight()) {

        Text(
            text = section.title,
            style = MaterialTheme.typography.body2,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 0.dp)
        )

        HorizontalPager(
            count = section.section_items.size,
            state = pagerState,
            modifier = Modifier
                .height(175.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            itemSpacing = 8.dp
        ) { index ->

            val item = section.section_items[index]

            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {

                AsyncImage(
                    model = item.image_url,
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
                        text = item.title,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = SemiBold,
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    Text(
                        text = item.subtitle,
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
fun RecommendedItem(pagerState: PagerState, section: Section) {

    Column(modifier = Modifier.wrapContentHeight()) {

        Text(
            text = section.title,
            style = MaterialTheme.typography.body2,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 0.dp)
        )

        HorizontalPager(
            count = section.section_items.size,
            state = pagerState,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            itemSpacing = 8.dp
        ) { index ->

            val item = section.section_items[index]

            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {

                AsyncImage(
                    model = item.image_url,
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
                        text = item.title,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = SemiBold,
                        modifier = Modifier.fillMaxWidth(.5f)
                    )

                    Text(
                        text = item.subtitle,
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


object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}
