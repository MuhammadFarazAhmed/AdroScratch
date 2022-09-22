package com.example.adro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.adro.AdroNavigationDestination
import com.example.home.nav.HomeDestination
import com.example.home.nav.homeGraph
import com.example.offers.nav.favGraph
import com.example.offers.nav.merchantGraph
import com.example.profile.nav.profileGraph

@Composable
fun AdroNavHost(
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
        merchantGraph()
        favGraph()
        profileGraph()

    }
}