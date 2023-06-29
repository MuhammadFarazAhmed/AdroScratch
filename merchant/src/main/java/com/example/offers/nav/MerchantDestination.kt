package com.example.offers.nav

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import com.example.adro.ui.ThriveNavigationDestination
import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen
import com.example.offers.ui.SearchScreen

object MerchantDestination : ThriveNavigationDestination {
    override val route = "merchant_route"
    override val destination = "offers_destination"

    const val detail = "merchant_detail"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.merchantGraph(navigateToDetail: () -> Unit) {

    composable(
        MerchantDestination.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "adoentertainer://offers"
                action = Intent.ACTION_VIEW
            }
        )
    )
    {
        OffersScreen(navigateToDetail, fetchParamsFromDeeplink())
    }
    composable(MerchantDestination.detail) {
        MerchantDetailScreen()
    }
}

@Composable
private fun fetchParamsFromDeeplink(): HashMap<String, String> {

    val params = hashMapOf<String, String>()

    val context = LocalContext.current
    val activity = context.findActivity()
    val queryParamsKeys = activity?.intent?.data?.queryParameterNames

    // GET ARGUMENTS FROM DEEPLINK
    queryParamsKeys?.forEach { queryParamKey ->
        params[queryParamKey] = activity.intent?.data?.getQueryParameter(queryParamKey) ?: ""
    }

    activity?.intent?.data = null
    return params
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
