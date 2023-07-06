@file:Suppress("FunctionName")

package com.example.adro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.example.adro.LocaleManager.setLocale
import com.example.adro.LocaleManager.setNewLocale
import com.example.adro.common.CommonUtilsExtension.LocalLocaleManager
import com.example.adro.prefs.PreferencesHelper
import com.example.adro.vm.CommonViewModel
import kotlinx.serialization.json.Json.Default.configuration
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavController

    private val vm: CommonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            return@setKeepOnScreenCondition vm.keepOnSplashScreenOn.value
        }

        setContent {
            LocalizedApp {
                val appState = rememberAdroAppState()
                navController = appState.navController
                ThriveApp(appState)
            }
        }
    }

//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(
//            setLocale(newBase)
//        )
//    }

    @Composable
    fun ProvideCurrentLocale(locale: Locale, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalCurrentLocale provides locale, content = content)
    }

    @Composable
    fun LocalizedApp(content: @Composable () -> Unit) {

        val currentLocale by remember { mutableStateOf(Locale.current) }
        var context = LocalContext.current
        val configuration = LocalConfiguration.current
        val localeManager = remember { LocaleManager }

        LaunchedEffect(configuration) {
            context = setNewLocale(context, "ar")
        }

        ProvideCurrentLocale(currentLocale) {
            // Compose UI
            content()
        }

//        CompositionLocalProvider(LocalLocaleManager provides localeManager) {
//            content()
//        }
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
                    // Check if the initial model is ready.
                    return if (!vm.keepOnSplashScreenOn.value) {
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