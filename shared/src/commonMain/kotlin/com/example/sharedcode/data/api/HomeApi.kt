package com.example.sharedcode.data.api

import com.example.sharedcode.domain.domain_model.HomeResponse

interface HomeApi {

  suspend  fun getHome(): HomeResponse

}