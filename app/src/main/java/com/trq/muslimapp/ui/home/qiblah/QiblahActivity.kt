package com.trq.muslimapp.ui.home.qiblah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityQiblahBinding

class QiblahActivity : AppCompatActivity() {

    private lateinit var binding : ActivityQiblahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQiblahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // setContentView(R.layout.activity_qiblah)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}