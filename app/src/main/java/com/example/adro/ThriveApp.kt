package com.example.adro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.adro.navigation.*
import com.example.adro.theme.ThriveScratchTheme
import com.example.offers.nav.findActivity

@Composable
fun ThriveApp(appState: ThriveAppState) {

    val topBarState: MutableState<Boolean> = rememberSaveable { (mutableStateOf(true)) }

    val bottomBarState: MutableState<Boolean> = rememberSaveable { (mutableStateOf(true)) }

    ThriveScratchTheme {

        Scaffold(

            topBar = { Toolbar(appState.shouldShowToolBar, appState::navigate) },

            content = { padding ->

                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxHeight()
                ) {

                    val context = LocalContext.current
                    val activity = context.findActivity()

                    ThriveNavHost(
                        navController = appState.navController,
                        popBack = appState::popBack,
                        handleDeepLinks = { appState.handleDeepLinks(activity?.intent) },
                        isApiLoading = { loading -> bottomBarState.value = !loading },
                        onNavigateToDestination = appState::navigate
                    )

                }

            }, bottomBar = {

                BottomNavigationBar(
                    bottomBarState = appState.shouldShowBottomBar,
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigate,
                    currentDestination = appState.currentDestination
                )

            })

    }

}

@Composable
private fun ToggleToolbarAndNavRail(
    appState: ThriveAppState,
    bottomBarState: MutableState<Boolean>,
    topBarState: MutableState<Boolean>
) {
    when (appState.currentDestination?.route) {
        "auth_route" -> {
            bottomBarState.value = false
            topBarState.value = false
        }

        "home_route" -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        "merchant_detail" -> {
            bottomBarState.value = false
            topBarState.value = false
        }

        "profile_route" -> {
            bottomBarState.value = true
            topBarState.value = false
        }

        "merchant_route" -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        "fav_route" -> {
            bottomBarState.value = true
            topBarState.value = true
        }
    }
}
