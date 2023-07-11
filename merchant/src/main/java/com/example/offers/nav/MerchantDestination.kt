package com.example.offers.nav

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import com.example.adro.common.CommonFlowExtensions
import com.example.adro.common.CommonFlowExtensions.fetchParamsFromDeeplink
import com.google.accompanist.navigation.animation.composable
import com.example.adro.ui.ThriveNavigationDestination
import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen
import com.example.offers.ui.SearchScreen

object MerchantDestination : ThriveNavigationDestination {
    override val route = "merchant_route"

    override val destination = "offers_destination"

    const val detail = "merchant_detail"

    const val specificOffers = "merchant_specific_category_route"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.merchantGraph(navigateToDetail: () -> Unit) {

    composable(
        MerchantDestination.route
    )
    { _ ->
        OffersScreen(navigateToDetail)
    }
    composable(MerchantDestination.detail) {
        MerchantDetailScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.specificOffers(navigateToDetail: () -> Unit) {
    composable(
        MerchantDestination.specificOffers,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "adoentertainer://offers"
                action = Intent.ACTION_VIEW
            }
        )
    )
    { _ ->
        OffersScreen(navigateToDetail, fetchParamsFromDeeplink())
    }
}