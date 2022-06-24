package com.trq.muslimapp.ui.home.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityDetailUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val USER = "user"
        const val EMAIL = "email"
        const val ROLE = "role"
        const val IMAGE_USER = "image_user"
        const val ID = "id"
    }

    lateinit var user : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Detail User")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.txtEmail.text = intent.getStringExtra(EMAIL)
        binding.txtName.text = intent.getStringExtra(USER)
        binding.txtRole.text = intent.getStringExtra(ROLE)

        binding.userId.text = intent.getStringExtra(ID)

        val imgUrl =
            "http://muslim.app.tiorisnanto.com/storage/${intent.getStringExtra(IMAGE_USER)}"

        Glide.with(this)
            .load(imgUrl)
            .error(R.drawable.user)
            .into(binding.imgUser)

        // setContentView(R.layout.activity_detail_user)

        binding.btnResetPassword.setOnClickListener {
            setupResetPassword()
        }
    }


    private fun setupResetPassword() {
        val idUsers = intent.getStringExtra(ID)
        ApiConfigRt.instanceRetrofit.resetPass(idUsers!!.toInt()).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful){
                    Toast.makeText(this@DetailUserActivity, "Berhasil reset", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@DetailUserActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}