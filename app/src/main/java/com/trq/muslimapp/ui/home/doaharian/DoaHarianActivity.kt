package com.trq.muslimapp.ui.home.doaharian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityDoaHarianBinding

class DoaHarianActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDoaHarianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoaHarianBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_doa_harian)
    }
}