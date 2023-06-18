package com.example.adro.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.adro.AdroNavigationDestination
import com.example.auth.nav.AuthDestination
import com.example.auth.nav.authGraph
import com.example.home.nav.HomeDestination
import com.example.home.nav.homeGraph
import com.example.offers.nav.MerchantDestination
import com.example.offers.nav.favGraph
import com.example.offers.nav.merchantGraph
import com.example.profile.nav.profileGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AdroNavHost(
    navController: NavHostController,
    onNavigateToDestination: (AdroNavigationDestination, String?) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HomeDestination.route
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        authGraph(onBackClick) {
                navController.popBackStack()
        }
        homeGraph(
            navigateToAuth = {
                onNavigateToDestination(AuthDestination, AuthDestination.route)
            },
            navigateToOffers = {
                onNavigateToDestination(MerchantDestination, MerchantDestination.route)
            }
        )
        merchantGraph(
            navigateToDetail = {
                onNavigateToDestination(MerchantDestination, MerchantDestination.detail)
            })
        favGraph()
        profileGraph()

    }
}