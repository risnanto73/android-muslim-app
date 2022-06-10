package com.trq.muslimapp.ui.home.quran

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivitySurahBinding
import com.trq.muslimapp.ui.home.quran.adapter.SurahAdapter
import com.trq.muslimapp.ui.home.quran.model.ResponseSurah
import com.trq.muslimapp.ui.home.quran.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurahActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySurahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_surah)

        val recSurah = findViewById<RecyclerView>(R.id.rv_surah)
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        progressDialog.show()


        ApiConfig.getApiService().getSurah().enqueue(object : Callback<ResponseSurah>{
            override fun onResponse(call: Call<ResponseSurah>, response: Response<ResponseSurah>) {
                if (response.isSuccessful){
                    progressDialog.dismiss()
                    val responseSurah = response.body()
                    val dataSurah = responseSurah?.data
                    val surahAdapter = SurahAdapter(dataSurah)
                    recSurah.apply {
                        layoutManager = LinearLayoutManager(this@SurahActivity)
                        setHasFixedSize(true)
                        surahAdapter.notifyDataSetChanged()
                        adapter = surahAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSurah>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}