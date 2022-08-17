package com.example.domain.models


import com.google.gson.annotations.SerializedName

data class TabsResponse(
    @SerializedName("cmd")
    val cmd: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("http_response")
    val httpResponse: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
) {
    data class Data(
        @SerializedName("limit")
        val limit: Int? = null,
        @SerializedName("tabs")
        val tabs: List<Tab?>? = null
    ) {
        data class Tab(
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("order")
            val order: Int? = null,
            @SerializedName("params")
            val params: Params? = null,
            @SerializedName("params_list")
            val paramsList: List<ParamsList?>? = null,
            @SerializedName("section_type")
            val sectionType: Int? = null,
            @SerializedName("uid")
            val uid: String? = null
        ) {
            data class Params(
                @SerializedName("category")
                val category: String? = null,
                @SerializedName("product_sku")
                val productSku: String? = null,
                @SerializedName("redeemability")
                val redeemability: String? = null,
                @SerializedName("tid")
                val tid: Int? = null
            )

            data class ParamsList(
                @SerializedName("key")
                val key: String? = null,
                @SerializedName("value")
                val value: String? = null
            )
        }
    }
}