package com.trq.muslimapp.ui.home.berita.activity

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.bumptech.glide.Glide
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityDetailBeritaBinding
import kotlinx.android.synthetic.main.activity_detail_berita.view.*
import kotlinx.android.synthetic.main.content_scrolling.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class DetailBeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("${intent.getStringExtra("judul")}")
        supportActionBar?.setSubtitle("${intent.getStringExtra("tanggal")}")

//        binding.toolbarLayout.title =  intent.getStringExtra("judul")

        binding.fab.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage(intent.getStringExtra("judul"))
                .setPositiveButton("Ya") { dialog, which ->
//                    Snackbar.make(binding.fab, "ok", Snackbar.LENGTH_LONG).show()
                }
                .show()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.lIsi.tvIsiBerita!!.text =
                Html.fromHtml(intent.getStringExtra("isi"), Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.lIsi.tvIsiBerita!!.text =
                Html.fromHtml(intent.getStringExtra("isi"))
        }

        val imgUrl =
            "http://muslim.app.tiorisnanto.com/storage/${intent.getStringExtra("gambar")}"

        Glide.with(this)
            .load(imgUrl)
            .into(binding.imgBerita)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}