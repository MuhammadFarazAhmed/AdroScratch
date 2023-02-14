package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
        @SerialName("section_identifier")
    var sectionIdentifier: String? = null,
        @SerialName("title")
    var title: String? = null,
        @SerialName("section_title")
    var sectionTitle: String? = null,
        @SerialName("sub_title")
    var subTitle: String? = null,
        @SerialName("button_title")
    var buttonTitle: String? = null,
        @SerialName("should_show_button")
    var shouldShowButton: Int? = null,
        @SerialName("button_bg_color")
    var buttonBgColor: String? = null,
        @SerialName("image_url")
    var imageUrl: String? = null,
        @SerialName("deeplink")
    var deeplink: String? = null,
        @SerialName("login_popup")
    var loginPopup: LoginPopup? = null,
        @SerialName("section_items")
    var sectionItems: List<SectionItem>? = null
)