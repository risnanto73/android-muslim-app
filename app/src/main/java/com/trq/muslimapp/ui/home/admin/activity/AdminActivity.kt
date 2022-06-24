package com.trq.muslimapp.ui.home.admin.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityAdminBinding
import com.trq.muslimapp.ui.home.admin.adapter.AllUserAdapter
import com.trq.muslimapp.ui.home.admin.model.ResponseAllUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("List User")
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupAllUser()

        binding.btnGo.setOnClickListener {
            startActivity(Intent(this, OtherMenuActivity::class.java))
        }

    }

    private fun setupAllUser() {
//        progressDialog.setTitle("Loading")
//        progressDialog.setMessage("Memuat data...")
//        progressDialog.setCancelable(false)
//        progressDialog.show()

        ApiConfigRt.instanceRetrofit.getAllUser().enqueue(object : Callback<ResponseAllUser> {
            override fun onResponse(
                call: Call<ResponseAllUser>,
                response: Response<ResponseAllUser>
            ) {
                val response = response.body()
                if (response != null) {
//                    progressDialog.dismiss()
                    val dataUser = response?.data
                    val userAdapter = AllUserAdapter(dataUser)
                    val recUser = findViewById<RecyclerView>(R.id.rv_user)
                    recUser.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@AdminActivity)
                        userAdapter.notifyDataSetChanged()
                        adapter = userAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseAllUser>, t: Throwable) {
                Toast.makeText(this@AdminActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}