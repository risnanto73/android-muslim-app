package com.trq.muslimapp.ui.home.quran.network

import com.trq.muslimapp.ui.home.quran.model.ResponseDetailSurah
import com.trq.muslimapp.ui.home.quran.model.ResponseSurah
import com.trq.muslimapp.ui.home.tahlil.model.ResponseYasin
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("surah")
    fun getSurah(): retrofit2.Call<ResponseSurah>

    @GET("surah/number")
    fun getSurahNumber(
        @Path("number") number: Int
    ): retrofit2.Call<ResponseDetailSurah>

    @GET("surah/36")
    fun getYasin(): retrofit2.Call<ResponseYasin>

}