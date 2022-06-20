package com.trq.muslimapp.ui.home.khutbah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityKhutbahBinding
import com.trq.muslimapp.ui.home.khutbah.adapter.KhutbahAdapter
import com.trq.muslimapp.ui.home.khutbah.adapter.KhutbahBeritaAdapter
import com.trq.muslimapp.ui.home.khutbah.model.ResponseKhutbah
import com.trq.muslimapp.ui.home.khutbah.ui.BeritaFragment
import com.trq.muslimapp.ui.home.khutbah.ui.KhutbahFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KhutbahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKhutbahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKhutbahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Khutbah & Berita")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.title = "Khutbah"
        setupTabs()
//        //setContentView(R.layout.activity_login)
    }

    private fun setupTabs() {
        val adapter = KhutbahBeritaAdapter(supportFragmentManager)
        adapter.addFragment(KhutbahFragment(), "Khutbah")
        adapter.addFragment(BeritaFragment(), "Berita")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
//    private fun setKhutbah() {
//        ApiConfigRt.instanceRetrofit.getKhutbah().enqueue(object : Callback<ResponseKhutbah>{
//            override fun onResponse(
//                call: Call<ResponseKhutbah>,
//                response: Response<ResponseKhutbah>
//            ) {
//                if (response.isSuccessful){
//                    val responseKhutbah = response.body()
//                    val dataKhutbah = responseKhutbah?.khutbah
//                    val khutbahAdapter = KhutbahAdapter(dataKhutbah)
//                    binding.rvKhutbah.apply {
//                        layoutManager = LinearLayoutManager(this@KhutbahActivity)
//                        setHasFixedSize(true)
//                        khutbahAdapter.notifyDataSetChanged()
//                        adapter = khutbahAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseKhutbah>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}