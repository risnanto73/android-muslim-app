package com.trq.muslimapp.ui.home.quran.viewmoel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trq.muslimapp.ui.home.quran.model.ModelAyat
import com.trq.muslimapp.ui.home.quran.model.ModelSurah
import com.trq.muslimapp.ui.home.quran.network.ApiInterface
import com.trq.muslimapp.ui.home.quran.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class SurahViewModel : ViewModel() {
    private val modelSurahMutableLiveData = MutableLiveData<ArrayList<ModelSurah>>()
    private val modelAyatMutableLiveData = MutableLiveData<ArrayList<ModelAyat>>()

    fun setSurah() {
        val apiService: ApiInterface = ApiService.getQuran()
        val call = apiService.getListSurah()

        call.enqueue(object : Callback<ArrayList<ModelSurah>> {
            override fun onResponse(call: Call<ArrayList<ModelSurah>>, response: Response<ArrayList<ModelSurah>>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items: ArrayList<ModelSurah> = ArrayList(response.body())
                    modelSurahMutableLiveData.postValue(items)
                }
            }

            override fun onFailure(call: Call<ArrayList<ModelSurah>>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun setDetailSurah(nomor: String) {
        val apiService: ApiInterface = ApiService.getQuran()
        val call = apiService.getDetailSurah(nomor)

        call.enqueue(object : Callback<ArrayList<ModelAyat>> {
            override fun onResponse(call: Call<ArrayList<ModelAyat>>, response: Response<ArrayList<ModelAyat>>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items: ArrayList<ModelAyat> = ArrayList(response.body())
                    modelAyatMutableLiveData.postValue(items)
                }
            }

            override fun onFailure(call: Call<ArrayList<ModelAyat>>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun getSurah(): LiveData<ArrayList<ModelSurah>> = modelSurahMutableLiveData

    fun getDetailSurah(): LiveData<ArrayList<ModelAyat>> = modelAyatMutableLiveData


}