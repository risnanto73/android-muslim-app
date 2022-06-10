package com.trq.muslimapp.ui.home.zakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityZakatBinding
import com.trq.muslimapp.ui.home.zakat.adapter.ZakatAdapter
import com.trq.muslimapp.ui.home.zakat.ui.ZakatDagangFragment
import com.trq.muslimapp.ui.home.zakat.ui.ZakatEmasFragment
import com.trq.muslimapp.ui.home.zakat.ui.ZakatPenghasilanFragment
import com.trq.muslimapp.ui.home.zakat.ui.ZakatTabunganFragment

class ZakatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityZakatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZakatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar!!.title = "Kalkulator Zakat"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ZakatAdapter(supportFragmentManager)
        adapter.addFragment(ZakatPenghasilanFragment(), "Zakat Penghasilan")
        adapter.addFragment(ZakatTabunganFragment(), "Zakat Tabungan")
        adapter.addFragment(ZakatEmasFragment(), "Zakat Emas")
        adapter.addFragment(ZakatDagangFragment(), "Zakat Dagang")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)?.setIcon(R.drawable.working)
        binding.tabs.getTabAt(1)?.setIcon(R.drawable.salary)
        binding.tabs.getTabAt(2)?.setIcon(R.drawable.treasure)
        binding.tabs.getTabAt(3)?.setIcon(R.drawable.seller)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}