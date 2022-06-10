package com.trq.muslimapp.ui.home.hijriyah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityHijriyahBinding

class HijriyahActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHijriyahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHijriyahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_hijriyah)
    }
}