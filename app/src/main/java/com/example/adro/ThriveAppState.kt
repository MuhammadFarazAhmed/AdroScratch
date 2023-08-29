package com.example.adro

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.core.os.trace
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.adro.navigation.ThriveNavigationDestination
import com.example.auth.nav.AuthDestination
import com.example.base.R
import com.example.home.nav.HomeDestination
import com.example.offers.nav.FavoriteDestination
import com.example.offers.nav.MerchantDestination
import com.example.offers.nav.SearchDestination
import com.example.profile.nav.ProfileDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAdroAppState(navController: NavHostController = rememberAnimatedNavController()): ThriveAppState {
    NavigationTrackingSideEffect(navController)
    return remember(navController) {
        ThriveAppState(navController)
    }
}

@Stable
class ThriveAppState(val navController: NavHostController) {


    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.route in topLevelDestinations.map { it.route }

    val shouldShowToolBar: Boolean
        @Composable get() = !(currentDestination?.route == FavoriteDestination.route ||
                currentDestination?.route == MerchantDestination.route ||
                currentDestination?.route == MerchantDestination.specificOffers ||
                currentDestination?.route == MerchantDestination.detail ||
                currentDestination?.route == ProfileDestination.route ||
                currentDestination?.route == AuthDestination.route ||
                currentDestination?.route == SearchDestination.route)


    /**
     * Top level destinations to be used in the BottomBar and NavRail
     */
    val topLevelDestinations: List<com.example.adro.navigation.TopLevelDestination> = listOf(
        com.example.adro.navigation.TopLevelDestination(
            route = HomeDestination.route,
            destination = HomeDestination.destination,
            selectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.home_select_icon),
            unselectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.home_unselect_icon),
            iconTextId = R.string.home
        ), com.example.adro.navigation.TopLevelDestination(
            route = MerchantDestination.route,
            destination = MerchantDestination.destination,
            selectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.offers_select_icon),
            unselectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.offer_unselect_icon),
            iconTextId = com.example.adro.R.string.offers
        ), com.example.adro.navigation.TopLevelDestination(
            route = FavoriteDestination.route,
            destination = FavoriteDestination.destination,
            selectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.favourite_select_icon),
            unselectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.favourite_unselect_icon),
            iconTextId = com.example.adro.R.string.Favorite
        ), com.example.adro.navigation.TopLevelDestination(
            route = ProfileDestination.route,
            destination = ProfileDestination.destination,
            selectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.profile_select_icon),
            unselectedIcon = com.example.adro.navigation.Icon.DrawableResourceIcon(R.drawable.profile_unselect_icon),
            iconTextId = com.example.adro.R.string.profile
        )
    )


    fun navigate(
        destination: ThriveNavigationDestination,
        route: String? = null,
        deeplink: String = ""
    ) {
        trace("Navigation: $destination") {

            if (deeplink.isNotEmpty()) {
                navController.navigate(route ?: destination.route)
                return@navigate
            }

            if (destination is com.example.adro.navigation.TopLevelDestination) {
                // if (route == "home_route") navController.popBackStack() //A BIG CHAPPI for deeplink
                navController.navigate(
                    route ?: destination.route
                ) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            } else {
                navController.navigate(route ?: destination.route)
            }
        }
    }

    fun popBack() {
        navController.popBackStack()
    }
}

/**
 * Stores information about navigation events to be used with JankStats
 */
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
//    JankMetricDisposableEffect(navController) { metricsHolder ->
//        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
//            metricsHolder.state?.putState("Navigation", destination.route.toString())
//        }
//
//        navController.addOnDestinationChangedListener(listener)
//
//        onDispose {
//            navController.removeOnDestinationChangedListener(listener)
//        }
//    }
}
