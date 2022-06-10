package com.trq.muslimapp.ui.home.tahlil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityTahlilBinding
import com.trq.muslimapp.ui.home.tahlil.adapter.TahlilAdapter
import com.trq.muslimapp.ui.home.tahlil.ui.TahlilFragment
import com.trq.muslimapp.ui.home.tahlil.ui.YasinFragment
import com.trq.muslimapp.ui.home.zakat.adapter.ZakatAdapter
import com.trq.muslimapp.ui.home.zakat.ui.ZakatDagangFragment
import com.trq.muslimapp.ui.home.zakat.ui.ZakatEmasFragment
import com.trq.muslimapp.ui.home.zakat.ui.ZakatPenghasilanFragment
import com.trq.muslimapp.ui.home.zakat.ui.ZakatTabunganFragment

class TahlilActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTahlilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTahlilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupTabs()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        // setContentView(R.layout.activity_tahlil)
    }

    private fun setupTabs() {
        val adapter = TahlilAdapter(supportFragmentManager)
        adapter.addFragment(TahlilFragment(), "Tahlil")
        adapter.addFragment(YasinFragment(), "Yasin")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }


}