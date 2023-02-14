package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersResponse(
    @SerialName("data")
    var `data`: Data? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("success")
    var success: Boolean? = null,
    @SerialName("cmd")
    var cmd: String? = null,
    @SerialName("http_response")
    var httpResponse: Int? = null,
    @SerialName("code")
    var code: Int? = null
) {
    @Serializable
    data class Data(
        @SerialName("is_elastic_search_results")
        var isElasticSearchResults: Boolean? = null,
        @SerialName("is_elastic_search_server_down")
        var isElasticSearchServerDown: Boolean? = null,
        @SerialName("ping_section")
        var pingSection: PingSection? = null,
        @SerialName("search_results")
        var searchResults: List<String?>? = null,
        @SerialName("featured_merchants")
        var featuredMerchants: List<String?>? = null,
        @SerialName("outlets")
        var outlets: List<Outlet>? = null,
        @SerialName("favourite_merchant")
        var favouriteMerchant: Int? = null,
        @SerialName("total_records")
        var totalRecords: Int? = null,
        @SerialName("show_delivery_purchase_view")
        var showDeliveryPurchaseView: Boolean? = null,
        @SerialName("records_in_current_page")
        var recordsInCurrentPage: Int? = null,
        @SerialName("offset")
        var offset: Int? = null,
        @SerialName("page_size")
        var pageSize: Int? = null,
        @SerialName("map_zoom_level")
        var mapZoomLevel: Double? = null
    ) {
        @Serializable
        class PingSection

        @Serializable
        data class Outlet(
            @SerialName("concept_id")
            var conceptId: String? = null,
            @SerialName("id")
            var id: Int? = null,
            @SerialName("sfId")
            var sfId: String? = null,
            @SerialName("name")
            var name: String? = null,
            @SerialName("email")
            var email: String? = null,
            @SerialName("lat")
            var lat: Double? = null,
            @SerialName("lng")
            var lng: Double? = null,
            @SerialName("human_location")
            var humanLocation: String? = null,
            @SerialName("distance")
            var distance: Int? = null,
            @SerialName("merchant_logo_url")
            var merchantLogoUrl: String? = null,
            @SerialName("merchant_logo_small_url")
            var merchantLogoSmallUrl: String? = null,
            @SerialName("merchant_photo_url")
            var merchantPhotoUrl: String? = null,
            @SerialName("merchant_photo_small_url")
            var merchantPhotoSmallUrl: String? = null,
            @SerialName("is_favourite")
            var isFavourite: Boolean? = null,
            @SerialName("tag_type_points")
            var tagTypePoints: Boolean? = null,
            @SerialName("tag_type_rewards")
            var tagTypeRewards: Boolean? = null,
            @SerialName("tag_type_show_n_go")
            var tagTypeShowNGo: Boolean? = null,
            @SerialName("tags")
            var tags: List<Tag?>? = null,
            @SerialName("merchant")
            var merchant: Merchant? = null,
            @SerialName("merchant_name")
            var merchantName: String? = null,
            @SerialName("fuzzy_relevance")
            var fuzzyRelevance: Int? = null,
            @SerialName("product_id")
            var productId: List<Int?>? = null,
            @SerialName("product_sku")
            var productSku: List<String?>? = null,
            @SerialName("top_offer_redeemability")
            var topOfferRedeemability: Int? = null,
            @SerialName("is_redeemable")
            var isRedeemable: Boolean? = null,
            @SerialName("is_purchased")
            var isPurchased: Boolean? = null,
            @SerialName("locked_image_url")
            var lockedImageUrl: String? = null,
            @SerialName("attributes")
            var attributes: List<Attribute?>? = null,
            @SerialName("cuisines")
            var cuisines: String? = null,
            @SerialName("merchant_sub_categories")
            var merchantSubCategories: List<String?>? = null
        ) {
            @Serializable
            data class Tag(
                @SerialName("icon")
                var icon: String? = null,
                @SerialName("title")
                var title: String? = null,
                @SerialName("abbreviated_text")
                var abbreviatedText: String? = null,
                @SerialName("uid")
                var uid: String? = null,
                @SerialName("bg_color")
                var bgColor: String? = null,
                @SerialName("title_color")
                var titleColor: String? = null,
                @SerialName("order_id")
                var orderId: Int? = null,
                @SerialName("identifier")
                var identifier: String? = null
            )

            @Serializable
            data class Merchant(
                @SerialName("id")
                var id: Int? = null,
                @SerialName("name")
                var name: String? = null,
                @SerialName("name_for_outlet")
                var nameForOutlet: String? = null
            )

            @Serializable
            data class Attribute(
                @SerialName("type")
                var type: String? = null,
                @SerialName("value")
                var value: String? = null
            )
        }
    }
}