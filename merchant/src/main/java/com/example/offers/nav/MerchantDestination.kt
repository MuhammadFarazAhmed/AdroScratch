package com.example.offers.nav

import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import com.example.adro.common.CommonFlowExtensions.fetchParamsFromDeeplink
import com.example.adro.navigation.ThriveNavigationDestination
import com.example.adro.vm.CommonViewModel
import com.example.domain.models.OffersResponse
import com.google.accompanist.navigation.animation.composable

import com.example.offers.ui.MerchantDetailScreen
import com.example.offers.ui.OffersScreen
import com.google.gson.Gson

object MerchantDestination : ThriveNavigationDestination {
    override val route = "merchant_route"

    override val destination = "offers_destination"

    const val detail = "merchant_detail/{outlet}"

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
    composable(MerchantDestination.detail,
        arguments = listOf(
            navArgument("outlet") {
                type = MerchantParamType()
            }
        )) {
        val outlet = it.arguments?.getParcelable<OffersResponse.Data.Outlet>("outlet")
        if (outlet != null) {
            MerchantDetailScreen(vm, outlet)
        }
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

@Suppress("DEPRECATION")
class MerchantParamType : NavType<OffersResponse.Data.Outlet>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): OffersResponse.Data.Outlet? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): OffersResponse.Data.Outlet {
        return Gson().fromJson(value, OffersResponse.Data.Outlet::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: OffersResponse.Data.Outlet) {
        bundle.putParcelable(key, value)
    }

}