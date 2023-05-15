package com.example.adro

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.core.os.trace
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.adro.navigation.Icon
import com.example.adro.navigation.TopLevelDestination
import com.example.base.R
import com.example.home.nav.HomeDestination
import com.example.offers.nav.FavoriteDestination
import com.example.offers.nav.MerchantDestination
import com.example.profile.nav.ProfileDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAdroAppState(navController: NavHostController = rememberAnimatedNavController()): AdroAppState {
    NavigationTrackingSideEffect(navController)
    return remember(navController) {
        AdroAppState(navController)
    }
}

@Stable
class AdroAppState(val navController: NavHostController) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    /**
     * Top level destinations to be used in the BottomBar and NavRail
     */
    val topLevelDestinations: List<TopLevelDestination> =
        listOf(
            TopLevelDestination(
                route = HomeDestination.route,
                destination = HomeDestination.destination,
                selectedIcon = Icon.DrawableResourceIcon(R.drawable.home_select_icon),
                unselectedIcon = Icon.DrawableResourceIcon(R.drawable.home_unselect_icon),
                iconTextId = R.string.home
            ),
            TopLevelDestination(
                route = MerchantDestination.route,
                destination = MerchantDestination.destination,
                selectedIcon = Icon.DrawableResourceIcon(R.drawable.offers_select_icon),
                unselectedIcon = Icon.DrawableResourceIcon(R.drawable.offer_unselect_icon),
                iconTextId = com.example.adro.R.string.offers
            ),
            TopLevelDestination(
                route = FavoriteDestination.route,
                destination = FavoriteDestination.destination,
                selectedIcon = Icon.DrawableResourceIcon(R.drawable.favourite_select_icon),
                unselectedIcon = Icon.DrawableResourceIcon(R.drawable.favourite_unselect_icon),
                iconTextId = com.example.adro.R.string.Favorite
            ),
            TopLevelDestination(
                route = ProfileDestination.route,
                destination = ProfileDestination.destination,
                selectedIcon = Icon.DrawableResourceIcon(R.drawable.profile_select_icon),
                unselectedIcon = Icon.DrawableResourceIcon(R.drawable.profile_unselect_icon),
                iconTextId = com.example.adro.R.string.profile
            )
        )

    /**
     * UI logic for navigating to a particular destination in the app. The NavigationOptions to
     * navigate with are based on the type of destination, which could be a top level destination or
     * just a regular destination.
     *
     * Top level destinations have only one copy of the destination of the back stack, and save and
     * restore state whenever you navigate to and from it.
     * Regular destinations can have multiple copies in the back stack and state isn't saved nor
     * restored.
     *
     * @param destination: The [AdroNavigationDestination] the app needs to navigate to.
     * @param route: Optional route to navigate to in case the destination contains arguments.
     */
    fun navigate(
        destination: AdroNavigationDestination,
        route: String? = null
    ) {
        trace("Navigation: $destination") {

            if (destination is TopLevelDestination) {
              //  if (route == "home_route") navController.popBackStack() //A BIG CHAPPI for deeplink
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

    fun onBackClick() {
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
