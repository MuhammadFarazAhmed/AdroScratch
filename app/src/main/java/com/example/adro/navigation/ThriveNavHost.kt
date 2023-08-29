package com.example.adro.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.adro.common.CommonFlowExtensions.findActivity
import com.example.auth.nav.AuthDestination
import com.example.auth.nav.authGraph
import com.example.home.nav.HomeDestination
import com.example.home.nav.homeGraph
import com.example.offers.nav.MerchantDestination
import com.example.offers.nav.favGraph
import com.example.offers.nav.merchantGraph
import com.example.offers.nav.searchGraph
import com.example.offers.nav.specificOffers
import com.example.profile.nav.profileGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThriveNavHost(
    navController: NavHostController,
    onNavigateToDestination: (ThriveNavigationDestination, String?) -> Unit,
    popBack: () -> Unit,
    isApiLoading: (loading: Boolean) -> Unit,
    handleDeepLinks: (ThriveNavigationDestination, String?, deepLink: String) -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HomeDestination.homeGraphRoutePattern
) {
    val context = LocalContext.current
    val activity = context.findActivity()


    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {


        homeGraph(
            handleDeepLinks = { deeplink ->
                activity?.intent = Intent(Intent.ACTION_VIEW, Uri.parse(deeplink))
                handleDeepLinks(MerchantDestination, MerchantDestination.specificOffers, deeplink)
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
                specificOffers(navigateToDetail = {
                    onNavigateToDestination(MerchantDestination, MerchantDestination.detail)
                })
                searchGraph(navigateToDetail = {
                    onNavigateToDestination(MerchantDestination, MerchantDestination.detail)
                })
            }
        )

        authGraph(popBack, popBack)

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