package com.trq.muslimapp.ui.api

import com.trq.muslimapp.ui.home.tahlil.model.ResponseTahlil
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("6150cbaa46dca9b58227")
    fun getTahlil() : Call<ResponseTahlil>
}