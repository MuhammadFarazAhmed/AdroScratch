package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerDetail(
    @SerialName("banner_bg_color")
    val bannerBgColor: String? = null,
    @SerialName("banner_text")
    val bannerText: String? = null,
    @SerialName("banner_text_color")
    val bannerTextColor: String? = null,
    @SerialName("should_show_cancel_button")
    val shouldShowCancelButton: Boolean? = null
)