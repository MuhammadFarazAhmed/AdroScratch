package com.example.home.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adro.BottomNavigationBar
import com.example.adro.NavigationItem
import com.example.adro.Toolbar
import com.example.adro.theme.AdroScratchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val topBarState = rememberSaveable { (mutableStateOf(true)) }

            AdroScratchTheme {
                Scaffold(topBar = { Toolbar(topBarState) },
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            NavigationComponent(navController, topBarState)
                        }
                    }, bottomBar = { BottomNavigationBar(navController) })
            }
        }
    }

    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        topAppBar: MutableState<Boolean>
    ) {
        NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomeScreen(this@MainActivity)
                topAppBar.value = true
            }
            composable(NavigationItem.Offers.route) {
                //OffersScreen()
                topAppBar.value = false
            }
            composable(NavigationItem.Favorite.route) {
                //FavoriteScreen()
                topAppBar.value = false
            }
            composable(NavigationItem.Profile.route) {
                // ProfileScreen()
                topAppBar.value = false
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