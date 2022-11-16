package com.example.offers.nav

import android.content.Intent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.adro.AdroNavigationDestination
import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen

object MerchantDestination : AdroNavigationDestination {
    override val route = "merchant_route"
    override val destination = "offers_destination"

    const val detail = "merchant_detail"
}

fun NavGraphBuilder.merchantGraph(navigateToDetail: () -> Unit) {
    composable(
        MerchantDestination.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern =
                    "adoentertainer://offers/?category={name}&category_id={id}"
                action = Intent.ACTION_VIEW
            }),
        arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
            },
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { entry ->
        val categoryId = entry.arguments?.getString("id")
        val categoryname = entry.arguments?.getString("name")
        OffersScreen(navigateToDetail,categoryId,categoryname)
    }
    composable(MerchantDestination.detail) {
        MerchantDetailScreen()
    }
}
