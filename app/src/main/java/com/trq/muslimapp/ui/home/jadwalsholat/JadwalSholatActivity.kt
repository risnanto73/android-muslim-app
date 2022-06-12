package com.trq.muslimapp.ui.home.jadwalsholat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityJadwalSholatBinding

class JadwalSholatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJadwalSholatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalSholatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_jadwal_sholat)
    }
}