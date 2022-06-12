package com.trq.muslimapp.ui.home.quran.network

import com.trq.muslimapp.ui.home.quran.model.ModelAyat
import com.trq.muslimapp.ui.home.quran.model.ModelSurah
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.ArrayList


interface ApiInterface {

    @GET("/99c279bb173a6e28359c/data")
    fun getListSurah(): Call<ArrayList<ModelSurah>>

    @GET("/99c279bb173a6e28359c/surat/{nomor}")
    fun getDetailSurah(
        @Path("nomor") nomor: String
    ): Call<ArrayList<ModelAyat>>


}