package com.example.adro

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.os.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.adro.navigation.Icon
import com.example.adro.navigation.TopLevelDestination
import com.example.base.R
import com.example.home.nav.HomeDestination

@Composable
fun rememberAdroAppState(
    navController: NavHostController = rememberNavController()
): AdroAppState {
    NavigationTrackingSideEffect(navController)
    return remember(navController) {
        AdroAppState(navController)
    }
}

@Stable
class AdroAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

//    val shouldShowBottomBar: Boolean
//        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
//            windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

//    val shouldShowNavRail: Boolean
//        get() = !shouldShowBottomBar

    /**
     * Top level destinations to be used in the BottomBar and NavRail
     */
    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = HomeDestination.route,
            destination = HomeDestination.destination,
            selectedIcon = Icon.DrawableResourceIcon(R.drawable.home_unselect_icon),
            unselectedIcon = Icon.DrawableResourceIcon(R.drawable.home_unselect_icon),
            iconTextId = R.string.home
        )
//        TopLevelDestination(
//            route = BookmarksDestination.route,
//            destination = BookmarksDestination.destination,
//            selectedIcon = DrawableResourceIcon(NiaIcons.Bookmarks),
//            unselectedIcon = DrawableResourceIcon(NiaIcons.BookmarksBorder),
//            iconTextId = bookmarksR.string.saved
//        ),
//        TopLevelDestination(
//            route = InterestsDestination.route,
//            destination = InterestsDestination.destination,
//            selectedIcon = ImageVectorIcon(NiaIcons.Grid3x3),
//            unselectedIcon = ImageVectorIcon(NiaIcons.Grid3x3),
//            iconTextId = interestsR.string.interests
//        )
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
    fun navigate(destination: AdroNavigationDestination, route: String? = null) {
        trace("Navigation: $destination") {
            if (destination is TopLevelDestination) {
                navController.navigate(route ?: destination.route) {
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