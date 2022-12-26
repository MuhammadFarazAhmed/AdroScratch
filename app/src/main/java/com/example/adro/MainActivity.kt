package com.example.adro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val isLoading = MutableLiveData(true)

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                lifecycleScope.launch {
                    delay(2000)
                    isLoading.value = false
                }
                isLoading.value!!
            }

        }

        super.onCreate(savedInstanceState)
        setContent {
            val adroAppState = rememberAdroAppState()
            navController = adroAppState.navController
            AdroApp(adroAppState)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navController.handleDeepLink(intent)

    }
}