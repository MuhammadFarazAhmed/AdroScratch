package com.example.adro.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.adro.ui.AdroNavigationDestination
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
fun ThriveNavHost(
    navController: NavHostController,
    onNavigateToDestination: (AdroNavigationDestination, String?) -> Unit,
    onLoginSuccess: () -> Unit,
    isApiLoading: (loading: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HomeDestination.route
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        authGraph(onLoginSuccess) {
            navController.popBackStack()
        }
        homeGraph(
            navigateToAuth = {
                onNavigateToDestination(AuthDestination, AuthDestination.route)
            },
            navigateToOffers = {
                onNavigateToDestination(MerchantDestination, MerchantDestination.route)
            },
            isApiLoading = { loading: Boolean ->
                isApiLoading(loading)
            }
        )
        merchantGraph(
            navigateToDetail = {
                onNavigateToDestination(MerchantDestination, MerchantDestination.detail)
            })
        favGraph()
        profileGraph(
            navigateToHome = {
                onNavigateToDestination(HomeDestination, HomeDestination.route)
            }
        )

    }
}