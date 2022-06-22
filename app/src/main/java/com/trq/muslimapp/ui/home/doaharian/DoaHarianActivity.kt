package com.trq.muslimapp.ui.home.doaharian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityDoaHarianBinding
import com.trq.muslimapp.ui.home.doaharian.adapter.DoaHarianAdapter
import com.trq.muslimapp.ui.home.doaharian.model.ResponseDoaHarian
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaHarianActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDoaHarianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoaHarianBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Doa Harian"

        setupDoaHarian()
        // setContentView(R.layout.activity_doa_harian)
    }

    private fun setupDoaHarian() {
        ApiConfigRt.instanceRetrofit.getDzikir().enqueue(object : Callback<ResponseDoaHarian>{
            override fun onResponse(
                call: Call<ResponseDoaHarian>,
                response: Response<ResponseDoaHarian>
            ) {
                if (response.isSuccessful){
                    val data = response.body()?.dzikir
                    val doaAdapter = DoaHarianAdapter(data)
                    val recvDoa = findViewById<RecyclerView>(R.id.rvDoaHarian)
                    recvDoa.apply {
                        layoutManager = LinearLayoutManager(this@DoaHarianActivity)
                        setHasFixedSize(true)
                        doaAdapter.notifyDataSetChanged()
                        adapter = doaAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDoaHarian>, t: Throwable) {
                Toast.makeText(this@DoaHarianActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}