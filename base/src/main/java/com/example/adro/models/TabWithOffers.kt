package com.example.adro.models

data class TabWithOffers(
    val tab: TabsResponse.Data.Tab? = null,
    val offers: OffersResponse.Data.Outlet? = null
)