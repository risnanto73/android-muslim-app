package com.trq.muslimapp.ui.home.quran

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivitySurahBinding
import com.trq.muslimapp.ui.home.quran.adapter.SurahAdapter
import com.trq.muslimapp.ui.home.quran.model.ModelSurah
import com.trq.muslimapp.ui.home.quran.viewmoel.SurahViewModel
import java.util.ArrayList

class SurahActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySurahBinding

    lateinit var progressDialog: ProgressDialog
    lateinit var surahViewModel: SurahViewModel
    lateinit var surahAdapter: SurahAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "List Surah"
        //setContentView(R.layout.activity_surah)

        setInitLayout()
        setViewModel()

    }

    private fun setViewModel() {
        progressDialog.show()
        surahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SurahViewModel::class.java)
        surahViewModel.setSurah()
        surahViewModel.getSurah()
            .observe(this) { modelSurah: ArrayList<ModelSurah> ->
                if (modelSurah.size != 0) {
                    surahAdapter.setAdapter(modelSurah)
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(this, "Data Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
                progressDialog.dismiss()
            }
    }

    private fun setInitLayout() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Sedang menampilkan data...")

        val recSurah = findViewById<RecyclerView>(R.id.rv_surah)
        surahAdapter = SurahAdapter(this)
        recSurah.adapter = surahAdapter
        recSurah.setHasFixedSize(true)
        recSurah.layoutManager = LinearLayoutManager(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}