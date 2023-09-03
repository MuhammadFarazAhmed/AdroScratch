package com.example.offers.nav

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import com.example.adro.common.CommonFlowExtensions.fetchParamsFromDeeplink
import com.example.adro.navigation.ThriveNavigationDestination
import com.example.adro.vm.CommonViewModel
import com.example.domain.models.OffersResponse
import com.google.accompanist.navigation.animation.composable

import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen

object MerchantDestination : ThriveNavigationDestination {
    override val route = "merchant_route"

    override val destination = "offers_destination"

    const val detail = "merchant_detail"

    const val specificOffers = "merchant_specific_category_route"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.merchantGraph(
    vm: CommonViewModel,
    navigateToDetail: (outlet: OffersResponse.Data.Outlet?) -> Unit
) {

    composable(
        MerchantDestination.route
    )
    { _ ->
        OffersScreen(navigateToDetail)
    }
    composable(MerchantDestination.detail) {
        MerchantDetailScreen(vm)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.specificOffers(navigateToDetail: (outlet: OffersResponse.Data.Outlet?) -> Unit) {
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