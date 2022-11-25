package com.example.adro

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.adro.navigation.*
import com.example.adro.theme.AdroScratchTheme
import kotlinx.coroutines.delay

@Composable
fun AdroApp(appState: AdroAppState = rememberAdroAppState()) {
    
    val topBarState: MutableState<Boolean> = rememberSaveable { (mutableStateOf(true)) }
    
    val bottomBarState: MutableState<Boolean> = rememberSaveable { (mutableStateOf(true)) }
    
    AdroScratchTheme {
        
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
                topBarState.value = false
            }
        }
        
        Scaffold(topBar = { Toolbar(topBarState) }, content = { padding ->
            Box(modifier = Modifier.padding(padding).fillMaxHeight()) {
                AdroNavHost(navController = appState.navController,
                        onBackClick = appState::onBackClick,
                        onNavigateToDestination = appState::navigate)
            }
        }, bottomBar = {
            BottomNavigationBar(bottomBarState = bottomBarState,
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigate,
                    currentDestination = appState.currentDestination)
        })
        
    }
    
}
