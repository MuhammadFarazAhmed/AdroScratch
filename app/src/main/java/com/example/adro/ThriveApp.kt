package com.example.adro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.adro.navigation.BottomNavigationBar
import com.example.adro.navigation.ThriveNavHost
import com.example.adro.theme.ThriveScratchTheme
import com.example.adro.vm.CommonViewModel

@Composable
fun ThriveApp(appState: ThriveAppState, vm: CommonViewModel) {

    val bottomBarState: MutableState<Boolean> = rememberSaveable { (mutableStateOf(true)) }

    ThriveScratchTheme {

        Scaffold(modifier = Modifier,

            topBar = { Toolbar(appState.shouldShowToolBar, appState::navigate) },

            content = { padding ->

                Box(
                    modifier =  Modifier
                        .padding(padding)
                        .fillMaxHeight()
                ) {

                    ThriveNavHost(
                        navController = appState.navController,
                        popBack = appState::popBack,
                        handleDeepLinks = appState::navigate,
                        isApiLoading = { loading -> bottomBarState.value = !loading },
                        vm = vm,
                        onNavigateToDestination = appState::navigate
                    )

                }

            }, bottomBar = {

                BottomNavigationBar(
                    bottomBarState = appState.shouldShowBottomBar,
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigate,
                    isApiLoading = bottomBarState.value,
                    currentDestination = appState.currentDestination
                )

            })

    }

}
