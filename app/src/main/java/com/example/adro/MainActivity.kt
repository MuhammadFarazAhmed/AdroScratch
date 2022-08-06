package com.example.adro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.example.adro.ui.home.HomeScreen
import com.example.adro.ui.offers.OffersScreen
import com.example.adro.ui.profile.ProfileScreen
import com.example.adro.ui.theme.AdroScratchTheme
import com.example.adro.vm.FavoriteViewModel
import com.example.adro.vm.ProfileViewModel
import com.example.adro.vm.OffersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navigator = remember { Navigator() }

            AdroScratchTheme {
                Scaffold(
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            NavigationComponent(navController, navigator)
                        }
                    }, bottomBar = { BottomNavigationBar(navController) }
                )
            }
        }
    }

    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        navigator: Navigator
    ) {
        NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomeScreen()
            }
            composable(NavigationItem.Offers.route) {
                val offersViewModel by viewModels<OffersViewModel>()
                OffersScreen()
            }
            composable(NavigationItem.Favorite.route) {
                val favVm by viewModels<FavoriteViewModel>()
                FavoriteScreen(navigator)
            }
            composable(NavigationItem.Profile.route) {
                val profileVm by viewModels<ProfileViewModel>()
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