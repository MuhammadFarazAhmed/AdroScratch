package com.example.domain.models

data class TabWithOffers(
    val tab: TabsResponse.Data.Tab? = null,
    val offers: OffersResponse.Data.Outlet? = null
)