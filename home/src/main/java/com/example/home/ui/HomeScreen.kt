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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.base.R
import com.example.home.ui.models.Section
import com.example.home.ui.models.SectionItem
import com.example.home.vm.HomeViewModel
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun HomeScreenPreview() {
    
    val pagerState = rememberPagerState()
    val exclusivePagerState = rememberPagerState()
    val recommendedPagerState = rememberPagerState()
    
    val isLoggedIn by remember {
        mutableStateOf(false)
    }
    
    val homeList = remember {
        
        mutableStateListOf(Section("main_carousal",
                mutableListOf(SectionItem("",
                        "Learn More",
                        "adoentertainer://merchantdetail?o_id=96714&m_id=55192",
                        366,
                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/1+Thrive_Banners_NoText_2.8.22_Air+Balloon+ENG+342x200.jpg",
                        1,
                        1,
                        "",
                        "Fly High!"),
                        SectionItem("",
                                "Learn More",
                                "adoentertainer://merchantdetail?o_id=2002&m_id=1286",
                                367,
                                "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/2+Thrive_Banners_NoText_2.8.22_Beach+ENG+342x200.jpg",
                                1,
                                1,
                                "",
                                "Soak up the sun"),
                        SectionItem("",
                                "Learn More",
                                "adoentertainer://merchantdetail?o_id=12529&m_id=1134",
                                368,
                                "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/3+Thrive_Banners_NoText_2.8.22_Desert+ENG+342x200.jpg",
                                1,
                                1,
                                "",
                                "Adventure is calling"),
                        SectionItem("",
                                "Learn More",
                                "adoentertainer://merchantdetail?o_id=64045&m_id=37500",
                                371,
                                "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/home_screen/4+Thrive_Banners_NoText_2.8.22_Family+ENG+342x200.jpg",
                                1,
                                1,
                                "",
                                "Experience more this weekend")),
                1,
                ""),
                Section("categories",
                        mutableListOf(SectionItem("",
                                "Learn More",
                                "adoentertainer://offers?category=Restaurants%20and%20bars",
                                366,
                                "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Food_and_drink.png",
                                1,
                                1,
                                "",
                                "Food & Drink"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Beauty & Fitness"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Attraction & Leisure"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Beauty & Fitness"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Travel"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Fashion & Retail"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Everyday Services")),
                        1,
                        "Categories"),
                Section("exclusive_offers",
                        mutableListOf(SectionItem("",
                                "Learn More",
                                "adoentertainer://offers?category=Restaurants%20and%20bars",
                                366,
                                "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Food_and_drink.png",
                                1,
                                1,
                                "",
                                "Food & Drink"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Beauty & Fitness"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Attraction & Leisure"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Beauty & Fitness"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Travel"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Fashion & Retail"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Everyday Services")),
                        1,
                        "Exclusive offers from partners"),
                Section("recommended_offers",
                        mutableListOf(SectionItem("",
                                "Learn More",
                                "adoentertainer://offers?category=Restaurants%20and%20bars",
                                366,
                                "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Food_and_drink.png",
                                1,
                                1,
                                "",
                                "Food & Drink"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Beauty & Fitness"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Attraction & Leisure"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Beauty & Fitness"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Travel"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Fashion & Retail"),
                                SectionItem("",
                                        "Learn More",
                                        "adoentertainer://offers?category=Body",
                                        367,
                                        "https://b2bapptilescdn.theentertainerme.com/new_featured/ADO/Beauty_and_fitness.png",
                                        1,
                                        1,
                                        "",
                                        "Everyday Services")),
                        1,
                        "Recommended offers"))
        
    }
    
    if (isLoggedIn) {
        homeList.add(1, Section("login", mutableListOf(), 1, ""))
    } else homeList.removeAt(1)
    
    
    Surface(modifier = Modifier.background(Color.White)) {
        
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            
            items(homeList) { section ->
                
                when (section.section_identifier) {
                    
                    "main_carousal" -> MainCarousal(pagerState, section)
                    
                    "login" -> LoginView()
                    
                    "categories" -> Categories(section)
                    
                    "exclusive_offers" -> ExclusiveItem(pagerState = exclusivePagerState, section)
                    
                    "recommended_offers" -> RecommendedItem(pagerState = recommendedPagerState,
                            section)
                    
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
    
    val homeList = vm.sections.collectAsState(initial = emptyList())
    
    val isLoggedIn by remember {
        mutableStateOf(true)
    }

//    if (!isLoggedIn) {
//        homeList.value.get(1) = Section("login", mutableListOf(), 1, "")
//    } else
//        homeList.removeAt(1)
    
    Surface(modifier = Modifier.background(Color.White)) {
        
        homeList.value?.let { sections ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                
                items(sections) { section ->
                    
                    when (section.section_identifier) {
                        
                        "main_carousal" -> MainCarousal(pagerState, section)
                        
                        "login" -> LoginView()
                        
                        "categories" -> Categories(section)
                        
                        "exclusive_offers" -> ExclusiveItem(pagerState = exclusivePagerState,
                                section)
                        
                        "recommended_offers" -> RecommendedItem(pagerState = recommendedPagerState,
                                section)
                        
                    }
                    
                }
                
            }
        }
    }
    
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainCarousal(pagerState: PagerState, section: Section) {
    
    HorizontalPager(count = section.section_items.size,
            state = pagerState,
            modifier = Modifier.height(250.dp),
            contentPadding = PaddingValues(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 24.dp),
            itemSpacing = 8.dp) { index ->
        
        val item = section.section_items[index]
        
        Card(modifier = Modifier.fillMaxSize(),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)) {
            
            AsyncImage(model = item.image_url,
                    contentScale = ContentScale.Crop,
                    contentDescription = "")
            
            Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.horizontalGradient(listOf(Color.Black.copy(alpha = 0.7f),
                            Color.Black.copy(alpha = 0f)))))
            
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly) {
                
                Text(text = item.title,
                        color = Color.White,
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(.8f),
                        fontWeight = SemiBold)
                
                Button(onClick = { },
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        modifier = Modifier
                                .height(34.dp)
                                .width(100.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = HexToJetpackColor.getColor(
                                "e43338"))) {
                    Text(text = item.button_title, color = Color.White, fontSize = 12.sp)
                }
                
            }
        }
    }
}

@Composable
fun LoginView() {
    Card(shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
            border = BorderStroke(2.dp, HexToJetpackColor.getColor("acccbc")),
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight()) {
        
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
            
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                    Arrangement.SpaceAround,
                    Alignment.CenterVertically) {
                
                Image(painter = painterResource(id = R.drawable.ic_limited_offer_1),
                        contentDescription = "",
                        modifier = Modifier
                                .height(40.dp)
                                .width(40.dp))
                
                Text(text = "Login to enjoy \ndiscounts and offers",
                        modifier = Modifier.wrapContentWidth(),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.body1.copy(lineHeight = 21.sp))
                
                Button(onClick = { }) {
                    Text(text = "Login", fontSize = 14.sp, style = MaterialTheme.typography.body1)
                }
                
            }
            
            Text(text = "Thrive Club app is exclusively for Abu Dhabi Golden Visa holders",
                    maxLines = 1,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally)
                            .wrapContentHeight()
                            .background(HexToJetpackColor.getColor("acccbc"))
                            .padding(vertical = 8.dp),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center)
            
        }
        
    }
}

@Composable
fun Categories(section: Section) {
    Column {
        
        Text(text = section.title,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(start = 16.dp))
        
        LazyRow(modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp)) {
            
            items(section.section_items) { item ->
                Card(modifier = Modifier
                        .wrapContentHeight()
                        .padding(4.dp)
                        .width(115.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp)) {
                    
                    Column(modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                            horizontalAlignment = CenterHorizontally) {
                        
                        Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(109.dp)) {
                            
                            AsyncImage(model = item.image_url,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "")
                        }
                        
                        Text(text = item.title,
                                modifier = Modifier.padding(vertical = 8.dp),
                                fontSize = 12.sp)
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
        
        Text(text = section.title,
                style = MaterialTheme.typography.body2,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 0.dp))
        
        HorizontalPager(count = section.section_items.size,
                state = pagerState,
                modifier = Modifier
                        .height(175.dp)
                        .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                itemSpacing = 8.dp) { index ->
            
            val item = section.section_items[index]
            
            Card(modifier = Modifier.fillMaxSize(),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)) {
                
                AsyncImage(model = item.image_url,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "")
                
                Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.horizontalGradient(listOf(Color.Black.copy(alpha = 0.7f),
                                Color.Black.copy(alpha = 0f)))))
                
                Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp),
                        verticalArrangement = Arrangement.Center) {
                    
                    Text(text = item.title,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = SemiBold,
                            modifier = Modifier.fillMaxWidth(.5f))
                    
                    Text(text = item.subtitle,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = SemiBold,
                            modifier = Modifier.padding(top = 12.dp))
                    
                }
                
            }
        }
        
    }
    
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecommendedItem(pagerState: PagerState, section: Section) {
    
    Column(modifier = Modifier.wrapContentHeight()) {
        
        Text(text = section.title,
                style = MaterialTheme.typography.body2,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 0.dp))
        
        HorizontalPager(count = section.section_items.size,
                state = pagerState,
                modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                itemSpacing = 8.dp) { index ->
            
            val item = section.section_items[index]
            
            Card(modifier = Modifier.fillMaxSize(),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)) {
                
                AsyncImage(model = item.image_url,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "")
                
                Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.horizontalGradient(listOf(Color.Black.copy(alpha = 0.7f),
                                Color.Black.copy(alpha = 0f)))))
                
                Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly) {
                    
                    Text(text = item.title,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = SemiBold,
                            modifier = Modifier.fillMaxWidth(.5f))
                    
                    Text(text = item.subtitle,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = SemiBold,
                            modifier = Modifier.padding(top = 12.dp))
                    
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
