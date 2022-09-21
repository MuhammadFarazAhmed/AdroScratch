package com.example.adro.navigation

import com.example.base.R

sealed class NavigationItem(
    var route: String,
    var iconUnSelected: Int,
    var iconSelected: Int,
    var title: String
) {
    object Home : NavigationItem(
        "home",
        R.drawable.home_unselect_icon,
        R.drawable.home_select_icon,
        "Home"
    )
    object Offers : NavigationItem(
        "offers",
        R.drawable.offer_unselect_icon,
        R.drawable.offers_select_icon,
        "Offers"
    )
    object Favorite : NavigationItem(
        "favorite",
        R.drawable.favourite_unselect_icon,
        R.drawable.favourite_select_icon,
        "Favorite"
    )
    object Profile : NavigationItem(
        "profile",
        R.drawable.profile_unselect_icon,
        R.drawable.profile_select_icon,
        "Profile"
    )
}