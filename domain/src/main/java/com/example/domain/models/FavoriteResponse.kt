package com.example.domain.models


import com.google.gson.annotations.SerializedName

data class FavoriteResponse(
    @SerializedName("cmd")
    val cmd: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("http_response")
    val httpResponse: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
) {
    data class Data(
        @SerializedName("favourite_merchant")
        val favouriteMerchant: Int? = null,
        @SerializedName("featured_merchants")
        val featuredMerchants: List<Any?>? = null,
        @SerializedName("is_elastic_search_results")
        val isElasticSearchResults: Boolean? = null,
        @SerializedName("is_elastic_search_server_down")
        val isElasticSearchServerDown: Boolean? = null,
        @SerializedName("map_zoom_level")
        val mapZoomLevel: Double? = null,
        @SerializedName("offset")
        val offset: Int? = null,
        @SerializedName("outlets")
        val outlets: List<Outlet>,
        @SerializedName("page_size")
        val pageSize: Int? = null,
        @SerializedName("ping_section")
        val pingSection: PingSection? = null,
        @SerializedName("print_query")
        val printQuery: String? = null,
        @SerializedName("records_in_current_page")
        val recordsInCurrentPage: Int? = null,
        @SerializedName("search_results")
        val searchResults: List<Any?>? = null,
        @SerializedName("show_delivery_purchase_view")
        val showDeliveryPurchaseView: Boolean? = null,
        @SerializedName("total_records")
        val totalRecords: Int? = null
    ) {
        data class Outlet(
            @SerializedName("attributes")
            val attributes: List<Attribute?>? = null,
            @SerializedName("concept_id")
            val conceptId: String? = null,
            @SerializedName("distance")
            val distance: Int? = null,
            @SerializedName("email")
            val email: String? = null,
            @SerializedName("fuzzy_relevance")
            val fuzzyRelevance: Int? = null,
            @SerializedName("human_location")
            val humanLocation: String? = null,
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("is_favourite")
            val isFavourite: Boolean? = null,
            @SerializedName("is_purchased")
            val isPurchased: Boolean? = null,
            @SerializedName("is_redeemable")
            val isRedeemable: Boolean? = null,
            @SerializedName("lat")
            val lat: Double? = null,
            @SerializedName("lng")
            val lng: Double? = null,
            @SerializedName("locked_image_url")
            val lockedImageUrl: String? = null,
            @SerializedName("merchant")
            val merchant: Merchant? = null,
            @SerializedName("merchant_logo_small_url")
            val merchantLogoSmallUrl: String? = null,
            @SerializedName("merchant_logo_url")
            val merchantLogoUrl: String? = null,
            @SerializedName("merchant_name")
            val merchantName: String? = null,
            @SerializedName("merchant_photo_small_url")
            val merchantPhotoSmallUrl: String? = null,
            @SerializedName("merchant_photo_url")
            val merchantPhotoUrl: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("product_id")
            val productId: List<Int?>? = null,
            @SerializedName("product_sku")
            val productSku: List<String?>? = null,
            @SerializedName("sfId")
            val sfId: String? = null,
            @SerializedName("tag_type_points")
            val tagTypePoints: Boolean? = null,
            @SerializedName("tag_type_rewards")
            val tagTypeRewards: Boolean? = null,
            @SerializedName("tag_type_show_n_go")
            val tagTypeShowNGo: Boolean? = null,
            @SerializedName("tags")
            val tags: List<Tag?>? = null,
            @SerializedName("top_offer_redeemability")
            val topOfferRedeemability: Int? = null
        ) {
            data class Attribute(
                @SerializedName("type")
                val type: String? = null,
                @SerializedName("value")
                val value: String? = null
            )

            data class Merchant(
                @SerializedName("id")
                val id: Int? = null,
                @SerializedName("name")
                val name: String? = null,
                @SerializedName("name_for_outlet")
                val nameForOutlet: String? = null
            )

            data class Tag(
                @SerializedName("abbreviated_text")
                val abbreviatedText: String? = null,
                @SerializedName("bg_color")
                val bgColor: String? = null,
                @SerializedName("icon")
                val icon: String? = null,
                @SerializedName("identifier")
                val identifier: String? = null,
                @SerializedName("order_id")
                val orderId: Int? = null,
                @SerializedName("title")
                val title: String? = null,
                @SerializedName("title_color")
                val titleColor: String? = null,
                @SerializedName("uid")
                val uid: String? = null
            )
        }

        class PingSection
    }
}

fun FavoriteResponse.asList() = data.outlets
