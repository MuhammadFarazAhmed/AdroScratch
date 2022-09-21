package com.example.adro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.home.nav.HomeDestination
import com.example.home.nav.homeGraph

@Composable
fun NavHost(
    navController: NavHostController,
    onNavigateToDestination: (AdroNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HomeDestination.route,
    topAppBar: MutableState<Boolean>
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        homeGraph()
//        composable(NavigationItem.Offers.route) {
//            //OffersScreen()
//            topAppBar.value = false
//        }
//        composable(NavigationItem.Favorite.route) {
//            //FavoriteScreen()
//            topAppBar.value = false
//        }
//        composable(NavigationItem.Profile.route) {
//            // ProfileScreen()
//            topAppBar.value = false
//        }
    }
}