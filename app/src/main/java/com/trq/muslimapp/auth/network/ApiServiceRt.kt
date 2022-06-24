package com.trq.muslimapp.auth.network

import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.model.User
import com.trq.muslimapp.ui.home.admin.model.ResponseAllUser
import com.trq.muslimapp.ui.home.berita.model.ResponseBerita
import com.trq.muslimapp.ui.home.doaharian.model.ResponseDoaHarian
import com.trq.muslimapp.ui.home.headline.model.ResponseKhutbahBerita
import com.trq.muslimapp.ui.home.khutbah.model.ResponseKhutbah
import com.trq.muslimapp.ui.home.zakat.model.ResponseHargaEmas
import com.trq.muslimapp.ui.note.model.ResponseNote
import com.trq.muslimapp.ui.note.model.ResponseNoteAll
import com.trq.muslimapp.ui.note.model.ResponseUserId
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @FormUrlEncoded
    @POST("api/updateEmas/{id}")
    fun editEmas(
        @Path("id") id: Int,
        @Field("hargaemas") hargaemas: Int
    ) : Call<ResponseHargaEmas>


    @GET("api/allBerita")
    fun getBerita(): Call<ResponseBerita>

    @GET("api/allKhutbah")
    fun getKhutbah(): Call<ResponseKhutbah>

    @GET("api/allDzikir")
    fun getDzikir(): Call<ResponseDoaHarian>

    @FormUrlEncoded
    @POST("api/storeMutabaah")
    fun addNote(
        @Field("user_id") userId: String? = null,
        @Field("catatan") catatan: String? = null,
        @Field("deskripsi") deskripsi: String? = null,
    ): Call<ResponseNote>

    // get all note
    @GET("api/allMutabaah/")
    fun getNote(): Call<ResponseNoteAll>

    @GET("api/allMutabaah/{user_id}")
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


//    @Multipart
//    @FormUrlEncoded
//    @POST("api/updateUser/{id}")
//    fun updateUser(
//        @Path("id") id: String,
//        @Part("gambar") gambar: String? = null,
//        @Field("name") name: String? = null,
//        @Field("email") email: String? = null,
//    ): Call<ResponseUser>

    @GET("api/allBeritadanKhutbah")
    fun getBeritaDanKhutbah(): Call<ResponseKhutbahBerita>

    @FormUrlEncoded
    @POST("api/editPass/{id}")
    fun editPass(
        @Path("id") id: Int,
        @Field("old_password") old_password: String,
        @Field("new_password") new_password: String
    ): Call<ResponseUser>

    @GET("api/allUser")
    fun getAllUser(): Call<ResponseAllUser>

    @GET("api/resetPass/{id}")
    fun resetPass(
        @Path("id") id: Int
    ): Call<ResponseUser>

    @Multipart
    @POST("api/updateUser/{id}")
    fun updateProfile(
        @Path("id") id: Int,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<ResponseUser>

//    @Multipart
//    @POST("api/storeGambar/{id}")
//    fun uploadImage(
//        @Path("id") id: Int,
//        @Part image: MultipartBody.Part
//    ): Call<ResponseUser>
}