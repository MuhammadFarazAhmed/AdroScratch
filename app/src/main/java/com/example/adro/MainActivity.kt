package com.example.adro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.example.adro.vm.CommonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavController

    private val vm: CommonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                return@setKeepOnScreenCondition vm.keepOnSplashScreenOn.value
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

    private fun setupPreDrawListener() {
        // Set up an OnPreDrawListener to the root view.
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (vm.keepOnSplashScreenOn.value) {
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content is not ready; suspend.
                        false
                    }
                }
            }
        )
    }
}