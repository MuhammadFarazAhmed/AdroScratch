package com.example.offers.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen

object MerchantDestination : AdroNavigationDestination {
    override val route = "merchant_route"
    override val destination = "offers_destination"

    const val detail = "merchant_detail"
}

fun NavGraphBuilder.merchantGraph(navigateToDetail: () -> Unit) {
    composable(MerchantDestination.route) {
        OffersScreen(navigateToDetail)
    }
    composable(MerchantDestination.detail) {
        MerchantDetailScreen()
    }
}
