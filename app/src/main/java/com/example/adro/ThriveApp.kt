package com.example.adro

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.adro.common.CommonFlowExtensions.findActivity
import com.example.adro.navigation.BottomNavigationBar
import com.example.adro.navigation.ThriveNavHost
import com.example.adro.theme.ThriveScratchTheme
import com.example.offers.nav.MerchantDestination

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


                    ThriveNavHost(
                        navController = appState.navController,
                        popBack = appState::popBack,
                        handleDeepLinks = appState::navigate,
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
