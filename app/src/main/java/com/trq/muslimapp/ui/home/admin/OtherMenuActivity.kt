package com.trq.muslimapp.ui.home.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityOtherMenuBinding
import com.trq.muslimapp.ui.home.admin.adapter.OtherMenuAdapter
import com.trq.muslimapp.ui.home.admin.ui.EmasAdminFragment
import com.trq.muslimapp.ui.home.admin.ui.KhutbahAdminFragment

class OtherMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOtherMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_other_menu)
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = OtherMenuAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter
        adapter.addFragment(EmasAdminFragment(), "Emas")
        adapter.addFragment(KhutbahAdminFragment(), "Khutbah")
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)
        binding.tabs.getTabAt(1)
    }
}