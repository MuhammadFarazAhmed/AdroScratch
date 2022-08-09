package com.example.adro.models

data class Section(
    val section_identifier: String,
    val section_items: List<SectionItem>,
    val sort_order: Int,
    val title: String
)