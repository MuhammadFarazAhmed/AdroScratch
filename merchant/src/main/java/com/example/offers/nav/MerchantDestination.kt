package com.example.offers.nav

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.offers.ui.FavoriteScreen
import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen
import java.util.Hashtable

object MerchantDestination : AdroNavigationDestination {
    override val route = "merchant_route?deeplink={deeplink}"
    override val destination = "offers_destination"

    const val detail = "merchant_detail"
    const val fav = "fav"
}

fun NavGraphBuilder.merchantGraph(navigateToDetail: () -> Unit) {

    composable(
        MerchantDestination.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "adoentertainer://offers"
            }
        ),
        arguments = listOf(
            navArgument("deeplink") {
                type = NavType.StringType
                defaultValue = ""
            }
        ),
    )
    { entry ->
        // check if the arguments is taken by deeplink or parameter
        OffersScreen(navigateToDetail, fetchParamsFromDeeplink(entry))
    }
    composable(MerchantDestination.detail) {
        MerchantDetailScreen()
    }
}

@Composable
private fun fetchParamsFromDeeplink(entry: NavBackStackEntry): HashMap<String, String> {

    val params = hashMapOf<String, String>()

    val context = LocalContext.current
    val activity = context.findActivity()
    val queryParamsKeys = activity?.intent?.data?.queryParameterNames

    // GET ARGUMENTS FROM PARAMETER
    entry.arguments?.getString("deeplink")?.let {
        it.toUri().queryParameterNames.forEach { queryParamKey ->
            params[queryParamKey] = it.toUri().getQueryParameter(queryParamKey) ?: ""
        }
    }

    // GET ARGUMENTS FROM DEEPLINK
    queryParamsKeys?.forEach { queryParamKey ->
        params[queryParamKey] = activity.intent?.data?.getQueryParameter(queryParamKey) ?: ""
    }
    return params
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
