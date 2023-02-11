package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerDetail(
    @SerialName("banner_bg_color")
    var bannerBgColor: String? = null,
    @SerialName("banner_text")
    var bannerText: String? = null,
    @SerialName("banner_text_color")
    var bannerTextColor: String? = null,
    @SerialName("should_show_cancel_button")
    var shouldShowCancelButton: Boolean? = null
)