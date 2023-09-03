package com.example.domain.models


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MerchantDetailModel(
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val `data`: Data,
    @SerialName("code")
    val code: Int,
    @SerialName("cmd")
    val cmd: String,
    @SerialName("http_response")
    val httpResponse: Int
) {
    @Serializable
    data class Data(
        @SerialName("merchant_logo_url")
        val merchantLogoUrl: String,
        @SerialName("merchant_id")
        val merchantId: Int,
        @SerialName("merchant_name")
        val merchantName: String,
        @SerialName("details")
        val details: List<Detail>
    ) {
        @Serializable
        data class Detail(
            @SerialName("section_identifier")
            val sectionIdentifier: String,
            @SerialName("location_description")
            val locationDescription: String,
            @SerialName("lat")
            val lat: Double,
            @SerialName("image_url")
            val imageUrl: String,
            @SerialName("merchant_name")
            val merchantName: String,
            @SerialName("location_name")
            val locationName: String,
            @SerialName("info_button_title")
            val infoButtonTitle: String,
            @SerialName("is_fav")
            val isFav: Boolean,
            @SerialName("is_show_fav_btn")
            val isShowFavBtn: Boolean,
            @SerialName("favourite_merchant")
            val favouriteMerchant: Boolean,
            @SerialName("category_item")
            val categoryItem: List<CategoryItem>,
            @SerialName("section_title")
            val sectionTitle: String,
            @SerialName("outlets")
            val outlets: List<Outlet>,
            @SerialName("offers")
            val offers: List<Offer>,
            @SerialName("outlet_description")
            val outletDescription: String
        ) {
            @Serializable
            data class CategoryItem(
                @SerialName("item_name")
                val itemName: String,
                @SerialName("item_image")
                val itemImage: String
            )

            @Serializable
            data class Outlet(
                @SerialName("id")
                val id: Int,
                @SerialName("email")
                val email: String,
                @SerialName("telephone")
                val telephone: String,
                @SerialName("lat")
                val lat: Double,
                @SerialName("lng")
                val lng: Double,
                @SerialName("delivery_telephone")
                val deliveryTelephone: String,
                @SerialName("merlin_url")
                val merlinUrl: String,
                @SerialName("name")
                val name: String,
                @SerialName("description")
                val description: String,
                @SerialName("human_location")
                val humanLocation: String,
                @SerialName("neighborhood")
                val neighborhood: String,
                @SerialName("mall")
                val mall: String,
                @SerialName("hotel")
                val hotel: String,
                @SerialName("is_favourite")
                val isFavourite: Boolean,
                @SerialName("distance")
                val distance: Int
            )

            @Serializable
            data class Offer(
                @SerialName("product_id")
                val productId: String,
                @SerialName("purchase_product_id")
                val purchaseProductId: Int,
                @SerialName("product_sequence")
                val productSequence: Int,
                @SerialName("section_name")
                val sectionName: String,
                @SerialName("product_sku")
                val productSku: String,
                @SerialName("is_delivery_section")
                val isDeliverySection: Boolean,
                @SerialName("is_monthly_section")
                val isMonthlySection: Boolean,
                @SerialName("is_show_purchase_button")
                val isShowPurchaseButton: Boolean,
                @SerialName("offers_to_display")
                val offersToDisplay: List<OffersToDisplay>
            ) {
                @Serializable
                data class OffersToDisplay(
                    @SerialName("categories_analytics")
                    val categoriesAnalytics: String,
                    @SerialName("offer_id")
                    val offerId: Int,
                    @SerialName("category")
                    val category: String,
                    @SerialName("category_color")
                    val categoryColor: String,
                    @SerialName("name")
                    val name: String,
                    @SerialName("details")
                    val details: String,
                    @SerialName("offer_detail")
                    val offerDetail: String,
                    @SerialName("is_cheers")
                    val isCheers: Boolean,
                    @SerialName("voucher_type")
                    val voucherType: Int,
                    @SerialName("voucher_type_image")
                    val voucherTypeImage: String,
                    @SerialName("voucher_restriction1")
                    val voucherRestriction1: Int,
                    @SerialName("voucher_restriction2")
                    val voucherRestriction2: Int,
                    @SerialName("voucher_restrictions")
                    val voucherRestrictions: String,
                    @SerialName("savings_estimate")
                    val savingsEstimate: Int,
                    @SerialName("savings_estimate_aed")
                    val savingsEstimateAed: Int,
                    @SerialName("savings_estimate_local_currency")
                    val savingsEstimateLocalCurrency: Int,
                    @SerialName("redeemability")
                    val redeemability: Int,
                    @SerialName("is_topup_offer_allowed")
                    val isTopupOfferAllowed: Boolean,
                    @SerialName("is_pingable")
                    val isPingable: Boolean,
                    @SerialName("is_pinged")
                    val isPinged: Boolean,
                    @SerialName("is_show_smiles")
                    val isShowSmiles: Boolean,
                    @SerialName("smiles_earn_value")
                    val smilesEarnValue: Int,
                    @SerialName("smiles_burn_value")
                    val smilesBurnValue: Int,
                    @SerialName("dcp_license")
                    val dcpLicense: String,
                    @SerialName("valid_from_date")
                    val validFromDate: String,
                    @SerialName("expiration_date")
                    val expirationDate: String,
                    @SerialName("validity_date")
                    val validityDate: String,
                    @SerialName("outlet_ids")
                    val outletIds: List<Int>,
                    @SerialName("item_code")
                    val itemCode: String,
                    @SerialName("is_barcode_enabled")
                    val isBarcodeEnabled: Boolean,
                    @SerialName("is_point_based_offer")
                    val isPointBasedOffer: Boolean,
                    @SerialName("is_merlin_offer")
                    val isMerlinOffer: Boolean,
                    @SerialName("outlet_merlin_urls")
                    val outletMerlinUrls: String,
                    @SerialName("merlin_title")
                    val merlinTitle: String,
                    @SerialName("merlin_button_text")
                    val merlinButtonText: String,
                    @SerialName("message")
                    val message: String,
                    @SerialName("sub_detail_label")
                    val subDetailLabel: String,
                    @SerialName("additional_details")
                    val additionalDetails: List<AdditionalDetail>,
                    @SerialName("voucher_details")
                    val voucherDetails: List<VoucherDetail>,
                    @SerialName("voucher_rules_of_use")
                    val voucherRulesOfUse: List<String>,
                    @SerialName("sort_order")
                    val sortOrder: Int,
                    @SerialName("offer_redemption_type")
                    val offerRedemptionType: Int
                ) {
                    @Serializable
                    data class AdditionalDetail(
                        @SerialName("id")
                        val id: Int,
                        @SerialName("image")
                        val image: String,
                        @SerialName("title")
                        val title: String,
                        @SerialName("color")
                        val color: String
                    )

                    @Serializable
                    data class VoucherDetail(
                        @SerialName("id")
                        val id: Int,
                        @SerialName("image")
                        val image: String,
                        @SerialName("title")
                        val title: String,
                        @SerialName("color")
                        val color: String
                    )
                }
            }
        }
    }
}