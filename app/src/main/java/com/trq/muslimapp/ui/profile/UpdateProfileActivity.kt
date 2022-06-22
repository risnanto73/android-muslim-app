package com.trq.muslimapp.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.trq.muslimapp.databinding.ActivityUpdateProfileBinding
import com.trq.muslimapp.helpers.SharedPreference


class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateProfileBinding
    lateinit var sharedPreference: SharedPreference

    var imagePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = SharedPreference(this)
        val user = sharedPreference.getUser()
        user?.id

        binding.edtEmail.setText(user?.email)
        binding.edtName.setText(user?.name)
        
        // setContentView(R.layout.activity_update_profile)
    }

}