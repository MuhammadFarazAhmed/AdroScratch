import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.json.JSONObject


@Serializable
data class MerchantDetailModel(
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("http_response")
    val httpResponse: Int? = null
) {
    @Serializable
    data class Data(
        @SerialName("merchant_logo_url")
        val merchantLogoUrl: String? = null,
        @SerialName("merchant_id")
        val merchantId: Int? = null,
        @SerialName("merchant_name")
        val merchantName: String? = null,
        @SerialName("details")
        val details: List<Detail?>? = null
    ) {
        @Serializable
        data class Detail(
            @SerialName("section_identifier")
            val sectionIdentifier: String? = null,
            @SerialName("location_description")
            val locationDescription: String? = null,
            @SerialName("lat")
            val lat: Double? = null,
            @SerialName("image_url")
            val imageUrl: String? = null,
            @SerialName("merchant_name")
            val merchantName: String? = null,
            @SerialName("location_name")
            val locationName: String? = null,
            @SerialName("info_button_title")
            val infoButtonTitle: String? = null,
            @SerialName("is_fav")
            val isFav: Boolean? = null,
            @SerialName("is_show_fav_btn")
            val isShowFavBtn: Boolean? = null,
            @SerialName("favourite_merchant")
            val favouriteMerchant: Boolean? = null,
            @SerialName("category_item")
            val categoryItem: List<CategoryItem?>? = null,
            @SerialName("section_title")
            val sectionTitle: String? = null,
            @SerialName("outlets")
            val outlets: List<Outlet?>? = null,
            @SerialName("offers")
            val offers: List<Offer?>? = null,
            @SerialName("outlet_description")
            val outletDescription: String? = null
        ) {
            @Serializable
            data class CategoryItem(
                @SerialName("item_name")
                val itemName: String? = null,
                @SerialName("item_image")
                val itemImage: String? = null
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): CategoryItem? {

                        jsonObject?.run {
                            return CategoryItem(
                                optString("item_name"),
                                optString("item_image")
                            )
                        }
                        return null
                    }
                }
            }

            @Serializable
            data class Outlet(
                @SerialName("id")
                val id: Int? = null,
                @SerialName("email")
                val email: String? = null,
                @SerialName("telephone")
                val telephone: String? = null,
                @SerialName("lat")
                val lat: Double? = null,
                @SerialName("lng")
                val lng: Double? = null,
                @SerialName("delivery_telephone")
                val deliveryTelephone: String? = null,
                @SerialName("merlin_url")
                val merlinUrl: String? = null,
                @SerialName("name")
                val name: String? = null,
                @SerialName("description")
                val description: String? = null,
                @SerialName("human_location")
                val humanLocation: String? = null,
                @SerialName("neighborhood")
                val neighborhood: String? = null,
                @SerialName("mall")
                val mall: String? = null,
                @SerialName("hotel")
                val hotel: String? = null,
                @SerialName("is_favourite")
                val isFavourite: Boolean? = null,
                @SerialName("distance")
                val distance: Int? = null
            ) {
                companion object {
                    @JvmStatic
                    fun buildFromJson(jsonObject: JSONObject?): Outlet? {

                        jsonObject?.run {
                            return Outlet(
                                optInt("id"),
                                optString("email"),
                                optString("telephone"),
                                optDouble("lat"),
                                optDouble("lng"),
                                optString("delivery_telephone"),
                                optString("merlin_url"),
                                optString("name"),
                                optString("description"),
                                optString("human_location"),
                                optString("neighborhood"),
                                optString("mall"),
                                optString("hotel"),
                                optBoolean("is_favourite"),
                                optInt("distance")
                            )
                        }
                        return null
                    }
                }
            }

            @Serializable
            data class Offer(
                @SerialName("product_id")
                val productId: String? = null,
                @SerialName("purchase_product_id")
                val purchaseProductId: Int? = null,
                @SerialName("product_sequence")
                val productSequence: Int? = null,
                @SerialName("section_name")
                val sectionName: String? = null,
                @SerialName("product_sku")
                val productSku: String? = null,
                @SerialName("is_delivery_section")
                val isDeliverySection: Boolean? = null,
                @SerialName("is_monthly_section")
                val isMonthlySection: Boolean? = null,
                @SerialName("is_show_purchase_button")
                val isShowPurchaseButton: Boolean? = null,
                @SerialName("offers_to_display")
                val offersToDisplay: List<OffersToDisplay?>? = null
            ) {
                @Serializable
                data class OffersToDisplay(
                    @SerialName("categories_analytics")
                    val categoriesAnalytics: String? = null,
                    @SerialName("offer_id")
                    val offerId: Int? = null,
                    @SerialName("category")
                    val category: String? = null,
                    @SerialName("category_color")
                    val categoryColor: String? = null,
                    @SerialName("name")
                    val name: String? = null,
                    @SerialName("details")
                    val details: String? = null,
                    @SerialName("offer_detail")
                    val offerDetail: String? = null,
                    @SerialName("is_cheers")
                    val isCheers: Boolean? = null,
                    @SerialName("voucher_type")
                    val voucherType: Int? = null,
                    @SerialName("voucher_type_image")
                    val voucherTypeImage: String? = null,
                    @SerialName("voucher_restriction1")
                    val voucherRestriction1: Int? = null,
                    @SerialName("voucher_restriction2")
                    val voucherRestriction2: Int? = null,
                    @SerialName("voucher_restrictions")
                    val voucherRestrictions: String? = null,
                    @SerialName("savings_estimate")
                    val savingsEstimate: Int? = null,
                    @SerialName("savings_estimate_aed")
                    val savingsEstimateAed: Int? = null,
                    @SerialName("savings_estimate_local_currency")
                    val savingsEstimateLocalCurrency: Int? = null,
                    @SerialName("redeemability")
                    val redeemability: Int? = null,
                    @SerialName("is_topup_offer_allowed")
                    val isTopupOfferAllowed: Boolean? = null,
                    @SerialName("is_pingable")
                    val isPingable: Boolean? = null,
                    @SerialName("is_pinged")
                    val isPinged: Boolean? = null,
                    @SerialName("is_show_smiles")
                    val isShowSmiles: Boolean? = null,
                    @SerialName("smiles_earn_value")
                    val smilesEarnValue: Int? = null,
                    @SerialName("smiles_burn_value")
                    val smilesBurnValue: Int? = null,
                    @SerialName("dcp_license")
                    val dcpLicense: String? = null,
                    @SerialName("valid_from_date")
                    val validFromDate: String? = null,
                    @SerialName("expiration_date")
                    val expirationDate: String? = null,
                    @SerialName("validity_date")
                    val validityDate: String? = null,
                    @SerialName("outlet_ids")
                    val outletIds: List<Int?>? = null,
                    @SerialName("item_code")
                    val itemCode: String? = null,
                    @SerialName("is_barcode_enabled")
                    val isBarcodeEnabled: Boolean? = null,
                    @SerialName("is_point_based_offer")
                    val isPointBasedOffer: Boolean? = null,
                    @SerialName("is_merlin_offer")
                    val isMerlinOffer: Boolean? = null,
                    @SerialName("outlet_merlin_urls")
                    val outletMerlinUrls: String? = null,
                    @SerialName("merlin_title")
                    val merlinTitle: String? = null,
                    @SerialName("merlin_button_text")
                    val merlinButtonText: String? = null,
                    @SerialName("message")
                    val message: String? = null,
                    @SerialName("sub_detail_label")
                    val subDetailLabel: String? = null,
                    @SerialName("additional_details")
                    val additionalDetails: List<AdditionalDetail?>? = null,
                    @SerialName("voucher_details")
                    val voucherDetails: List<VoucherDetail?>? = null,
                    @SerialName("voucher_rules_of_use")
                    val voucherRulesOfUse: List<String?>? = null,
                    @SerialName("offer_redemption_type")
                    val offerRedemptionType: Int? = null,
                    @SerialName("sort_order")
                    val sortOrder: Int? = null
                ) {
                    @Serializable
                    data class AdditionalDetail(
                        @SerialName("id")
                        val id: Int? = null,
                        @SerialName("image")
                        val image: String? = null,
                        @SerialName("title")
                        val title: String? = null,
                        @SerialName("color")
                        val color: String? = null
                    )

                    @Serializable
                    data class VoucherDetail(
                        @SerialName("id")
                        val id: Int? = null,
                        @SerialName("image")
                        val image: String? = null,
                        @SerialName("title")
                        val title: String? = null,
                        @SerialName("color")
                        val color: String? = null
                    )
                }
            }
        }
    }
}