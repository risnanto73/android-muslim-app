package com.trq.muslimapp.ui.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfiguration {

    const val urlTahlil = "https://api.npoint.io/"

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlTahlil)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiInterface() : ApiInterface {
        return getRetrofit().create(ApiInterface::class.java)
    }

}