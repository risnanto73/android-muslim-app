package com.trq.muslimapp.ui.home.quran

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityDetailSurahBinding
import com.trq.muslimapp.ui.home.quran.adapter.DetailSurahAdapter
import com.trq.muslimapp.ui.home.quran.model.ModelAyat
import com.trq.muslimapp.ui.home.quran.model.ModelSurah
import com.trq.muslimapp.ui.home.quran.viewmoel.SurahViewModel
import java.util.ArrayList

class DetailSurahActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailSurahBinding

    lateinit var modelSurah: ModelSurah
    lateinit var detailAdapter: DetailSurahAdapter
    lateinit var progressDialog: ProgressDialog
    lateinit var surahViewModel: SurahViewModel
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Detail Surah")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // setContentView(R.layout.activity_detail_surah)

        val recDetailSurah = findViewById<RecyclerView>(R.id.rv_detail_surah)


        modelSurah = intent.getSerializableExtra(DETAIL_SURAH) as ModelSurah
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Sedang menampilkan data...")

        detailAdapter = DetailSurahAdapter()
        recDetailSurah.setHasFixedSize(true)
        recDetailSurah.layoutManager = LinearLayoutManager(this)
        recDetailSurah.adapter = detailAdapter

        setViewModel()
    }

    private fun setViewModel() {
        progressDialog.show()
        surahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SurahViewModel::class.java)
        surahViewModel.setDetailSurah(modelSurah.nomor)
        surahViewModel.getDetailSurah()
            .observe(this) { modelAyat: ArrayList<ModelAyat> ->
                if (modelAyat.size != 0) {
                    detailAdapter.setAdapter(modelAyat)
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(this, "Data Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
                progressDialog.dismiss()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val DETAIL_SURAH = "DETAIL_SURAH"
    }
}