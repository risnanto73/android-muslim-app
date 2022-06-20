package com.trq.muslimapp.ui.home.khutbah

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityDetailKhutbahBinding

class DetailKhutbahActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailKhutbahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKhutbahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Detail Khutbah"

        binding.tvPemateri.text = """
            Pemateri : ${intent.getStringExtra("pemateri")}
        """.trimIndent()
        binding.tvJudul.text = intent.getStringExtra("judul")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvIsi.text =
                Html.fromHtml(intent.getStringExtra("isi"), Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.tvIsi.text =
                Html.fromHtml(intent.getStringExtra("isi"))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}