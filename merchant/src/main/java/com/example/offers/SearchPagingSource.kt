package com.example.offers

import com.example.adro.paging.BasePagingSource

class SearchPagingSource<Item : Any>(private val query: String) : BasePagingSource<Item>() {



}