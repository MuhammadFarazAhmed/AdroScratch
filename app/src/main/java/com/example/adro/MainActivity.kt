package com.example.adro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adro.ui.favorite.FavoriteScreen
import com.example.adro.theme.AdroScratchTheme
import com.example.home.ui.HomeScreen
import com.example.offers.ui.OffersScreen
import com.example.profile.ui.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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

    @Composable
    fun NavigationComponent(navController: NavHostController, navigator: Navigator) {
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
                ProfileScreen()
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