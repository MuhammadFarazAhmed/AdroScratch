package com.example.sharedcode.offers.data.api

import com.example.sharedcode.domain.domain_model.HomeResponse

interface MerchantApi {

  suspend  fun getHome(): HomeResponse

}