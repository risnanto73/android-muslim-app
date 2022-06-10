package com.trq.muslimapp.rt.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PiApiConfig {

    const val BASE_URL = "http://muslim.app.tiorisnanto.com/"

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    fun getApiService() : PiApiService{
        return getRetrofit().create(PiApiService::class.java)
    }

}