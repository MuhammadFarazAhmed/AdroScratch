package com.example.sharedcode.offers.data.api

import com.example.sharedcode.domain.domain_model.HomeResponse
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse

interface MerchantApi {

  suspend  fun getTabs(): TabsResponse
  suspend  fun fetchOffers(): OffersResponse

}