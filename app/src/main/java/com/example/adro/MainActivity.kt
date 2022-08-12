package com.example.adro

import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adro.ui.favorite.FavoriteScreen
import com.example.adro.ui.offers.OffersScreen
import com.example.adro.ui.profile.ProfileScreen
import com.example.adro.ui.theme.AdroScratchTheme
import com.example.home.ui.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navigator = remember { Navigator() }
            
            AdroScratchTheme {
                Scaffold(topBar = { Toolbar() },
                        content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        NavigationComponent(navController, navigator)
                    }
                }, bottomBar = { BottomNavigationBar(navController) })
            }
        }
    }
    
    @Composable fun NavigationComponent(navController: NavHostController, navigator: Navigator) {
        NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomeScreen()
            }
            composable(NavigationItem.Offers.route) {
                OffersScreen()
            }
            composable(NavigationItem.Favorite.route) {
                FavoriteScreen(navigator)
            }
            composable(NavigationItem.Profile.route) {
                ProfileScreen(navigator)
            }
        }

//        another method for custom navgation
//        LaunchedEffect("navigation") {
//            navigator.sharedFlow.onEach {
//                navController.navigate(it.label)
//            }.launchIn(this)
//        }
    }
}