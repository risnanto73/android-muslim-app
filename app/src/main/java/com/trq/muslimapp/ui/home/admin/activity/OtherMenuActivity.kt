package com.trq.muslimapp.ui.home.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.databinding.ActivityOtherMenuBinding
import com.trq.muslimapp.ui.home.admin.adapter.OtherMenuViewAdapter
import com.trq.muslimapp.ui.home.admin.ui.EmasAdminFragment
import com.trq.muslimapp.ui.home.admin.ui.KhutbahAdminFragment

class OtherMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOtherMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Menu Admin")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // setContentView(R.layout.activity_other_menu)
        setupTabs()
    }

    private fun setupTabs() {
       val adapter = OtherMenuViewAdapter(supportFragmentManager)
        adapter.addFragment(EmasAdminFragment(), "Emas & Berita")
//        adapter.addFragment(KhutbahAdminFragment(), "Khutbah")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)?.text = "Emas"
//        binding.tabs.getTabAt(1)?.text = "Khutbah"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}