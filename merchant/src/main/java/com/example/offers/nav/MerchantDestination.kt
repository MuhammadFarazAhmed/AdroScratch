
package com.example.offers.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.offers.ui.OffersScreen

object MerchantDestination : AdroNavigationDestination {
    override val route = "merchant_route"
    override val destination = "offers_destination"
}

fun NavGraphBuilder.merchantGraph() {
    composable(MerchantDestination.route) {
        OffersScreen()
    }
}
