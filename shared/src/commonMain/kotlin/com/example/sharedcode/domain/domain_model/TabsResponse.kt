package com.example.sharedcode.domain.domain_model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TabsResponse(
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("http_response")
    val httpResponse: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("success")
    val success: Boolean? = null
) {
    @kotlinx.serialization.Serializable
    data class Data(
        @SerialName("limit")
        val limit: Int? = null,
        @SerialName("tabs")
        val tabs: List<Tab?>? = null
    ) {
        @kotlinx.serialization.Serializable
        data class Tab(
            @SerialName("name")
            val name: String? = null,
            @SerialName("order")
            val order: Int? = null,
            @SerialName("params")
            val params: Params? = null,
            @SerialName("params_list")
            val paramsList: List<ParamsList?>? = null,
            @SerialName("section_type")
            val sectionType: Int? = null,
            @SerialName("uid")
            val uid: String? = null
        ) {
            @kotlinx.serialization.Serializable
            data class Params(
                @SerialName("category")
                val category: String? = null,
                @SerialName("product_sku")
                val productSku: String? = null,
                @SerialName("redeemability")
                val redeemability: String? = null,
                @SerialName("tid")
                val tid: Int? = null
            )

            @kotlinx.serialization.Serializable
            data class ParamsList(
                @SerialName("key")
                val key: String? = null,
                @SerialName("value")
                val value: String? = null
            )
        }
    }
}