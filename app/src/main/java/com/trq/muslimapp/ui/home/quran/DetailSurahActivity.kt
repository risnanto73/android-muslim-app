package com.trq.muslimapp.ui.home.quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityDetailSurahBinding
import com.trq.muslimapp.ui.home.quran.adapter.DetailSurahAdapter
import com.trq.muslimapp.ui.home.quran.model.ResponseDetailSurah
import com.trq.muslimapp.ui.home.quran.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailSurahActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailSurahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Detail Surah")
        // setContentView(R.layout.activity_detail_surah)

        val recDetailSurah = findViewById<RecyclerView>(R.id.rv_detail_surah)
        recDetailSurah.layoutManager = LinearLayoutManager(this)


    }
}