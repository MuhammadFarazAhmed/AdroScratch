package com.example.offers.nav

import android.content.Intent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.adro.AdroNavigationDestination
import com.example.offers.ui.FavoriteScreen
import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen

object MerchantDestination : AdroNavigationDestination {
    override val route = "merchant_route"
    override val destination = "offers_destination"

    const val detail = "merchant_detail"
    const val fav = "fav"
}

fun NavGraphBuilder.merchantGraph(navigateToDetail: () -> Unit) {
    composable(MerchantDestination.route) {
        OffersScreen(navigateToDetail, "categoryId", "categoryname")

    }
    composable(MerchantDestination.detail) {
        MerchantDetailScreen()
    }
}
