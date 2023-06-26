package com.example.adro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.adro.navigation.*
import com.example.adro.theme.ThriveScratchTheme
import com.example.adro.ui.Toolbar

@Composable
fun ThriveApp(appState: AdroAppState) {

    val topBarState: MutableState<Boolean> = rememberSaveable { (mutableStateOf(true)) }

    val bottomBarState: MutableState<Boolean> = rememberSaveable { (mutableStateOf(true)) }

    ThriveScratchTheme {

        //ToggleToolbarAndNavRail(appState, bottomBarState, topBarState)

        Scaffold(

            topBar = { Toolbar(appState.shouldShowToolBar) },

            content = { padding ->

                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxHeight()
                ) {

                    ThriveNavHost(
                        navController = appState.navController,
                        popBack = appState::popBack,
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
    appState: AdroAppState,
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
