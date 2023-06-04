package com.example.domain.models


import kotlinx.collections.immutable.PersistentList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigModel(
    @SerialName("data")
    var `data`: Data? = null,
    @SerialName("success")
    var success: Boolean? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("cmd")
    var cmd: String? = null,
    @SerialName("http_response")
    var httpResponse: Int? = null,
    @SerialName("code")
    var code: Int? = null
) {
    @Serializable
    data class Data(
        @SerialName("pending_transaction_deeplink")
        var pendingTransactionDeeplink: String? = null,
        @SerialName("banner_detail")
        var bannerDetail: BannerDetail? = null,
        @SerialName("sections")
        var sections: PersistentList<Section?>? = null
    ) {
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
            var sectionItems: PersistentList<SectionItem?>? = null
        ) {
            @Serializable
            data class LoginPopup(
                @SerialName("title")
                var title: String? = null,
                @SerialName("message")
                var message: String? = null,
                @SerialName("button_title")
                var buttonTitle: String? = null
            )

            @Serializable
            data class SectionItem(
                @SerialName("is_external_link")
                var isExternalLink: Int? = null,
                @SerialName("title")
                var title: String? = null,
                @SerialName("image_url")
                var imageUrl: String? = null,
                @SerialName("deeplink")
                var deeplink: String? = null,
                @SerialName("id")
                var id: Int? = null,
                @SerialName("subtitle")
                var subtitle: String? = null
            )
        }
    }
}