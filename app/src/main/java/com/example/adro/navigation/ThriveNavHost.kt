package com.example.adro.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.adro.ui.ThriveNavigationDestination
import com.example.auth.nav.AuthDestination
import com.example.auth.nav.authGraph
import com.example.home.nav.HomeDestination
import com.example.home.nav.homeGraph
import com.example.offers.nav.FavoriteDestination
import com.example.offers.nav.MerchantDestination
import com.example.offers.nav.favGraph
import com.example.offers.nav.merchantGraph
import com.example.offers.nav.searchGraph
import com.example.offers.ui.FavoriteScreen
import com.example.profile.nav.profileGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThriveNavHost(
    navController: NavHostController,
    onNavigateToDestination: (ThriveNavigationDestination, String?) -> Unit,
    popBack: () -> Unit,
    isApiLoading: (loading: Boolean) -> Unit,
    handleDeepLinks: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = "home_graph"
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {


        homeGraph(
            handleDeepLinks = {
                handleDeepLinks()
            },
            navigateToAuth = {
                onNavigateToDestination(AuthDestination, AuthDestination.route)
            },
            navigateToOffers = {
                onNavigateToDestination(MerchantDestination, MerchantDestination.route)
            },
            isApiLoading = { loading: Boolean ->
                isApiLoading(loading)
            },
            nestedGraphs = {
                authGraph(popBack, popBack)
                searchGraph(navigateToDetail = {
                    onNavigateToDestination(MerchantDestination, MerchantDestination.detail)
                })
            }
        )

        merchantGraph(
            navigateToDetail = {
                onNavigateToDestination(MerchantDestination, MerchantDestination.detail)
            })

        favGraph()

        profileGraph(
            navigateToHome = {
                onNavigateToDestination(HomeDestination, HomeDestination.homeGraphRoutePattern)
            }
        )

    }
}