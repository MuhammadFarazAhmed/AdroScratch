package com.example.adro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.example.adro.vm.CommonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavController

    private val commonViewModel: CommonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                commonViewModel.getConfig()
                commonViewModel.isConfigSuccess.value!!
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