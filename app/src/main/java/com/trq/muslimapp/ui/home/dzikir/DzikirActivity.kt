package com.trq.muslimapp.ui.home.dzikir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityDzikirBinding

class DzikirActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDzikirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dzikir)
        binding = ActivityDzikirBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}