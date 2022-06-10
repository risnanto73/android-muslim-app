package com.trq.muslimapp.rt.api

import com.trq.muslimapp.rt.model.ResponseEmas
import retrofit2.http.GET

interface PiApiService {

    @GET("api/allEmas")
    fun getAllEmas(): retrofit2.Call<ResponseEmas>
}