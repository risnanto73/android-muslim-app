package com.trq.muslimapp.auth.network

import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.ui.home.berita.model.ResponseBerita
import com.trq.muslimapp.ui.home.khutbah.model.ResponseKhutbah
import com.trq.muslimapp.ui.home.zakat.model.ResponseHargaEmas
import com.trq.muslimapp.ui.note.model.ResponseNote
import com.trq.muslimapp.ui.note.model.ResponseNoteAll
import com.trq.muslimapp.ui.note.model.ResponseUserId
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceRt {

    @FormUrlEncoded
    @POST("/api/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseUser>

    @FormUrlEncoded
    @POST("/api/regis")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseUser>

    @GET("api/allEmas")
    fun getEmas(): Call<ResponseHargaEmas>

    @GET("api/allBerita")
    fun getBerita(): Call<ResponseBerita>

    @GET("api/allKhutbah")
    fun getKhutbah(): Call<ResponseKhutbah>

    @FormUrlEncoded
    @POST("api/storeMutabaah")
    fun addNote(
        @Field("user_id") userId: String? = null,
        @Field("catatan") catatan: String? = null,
        @Field("deskripsi") deskripsi: String? = null,
    ): Call<ResponseNote>


    @GET("api/allMutabaah/")  // get all note
    fun getNote(): Call<ResponseNoteAll>

    @GET("api/detailMutabaah/{user_id}")
    fun getNoteById(
        @Path("user_id") user_id: String
    ): Call<ResponseUserId>

    @FormUrlEncoded
    @POST("api/updateMutabaah/{id}")
    fun updateNote(
        @Path("id") id: String,
        @Field("user_id") userId: String? = null,
        @Field("catatan") catatan: String? = null,
        @Field("deskripsi") deskripsi: String? = null,
    ): Call<ResponseNote>

    @DELETE("api/deleteMutabaah/{id}")
    fun deleteNote(
        @Path("id") id: Int
    ): Call<ResponseNote>

}