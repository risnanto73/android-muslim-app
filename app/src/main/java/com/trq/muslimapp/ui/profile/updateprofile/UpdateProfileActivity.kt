package com.trq.muslimapp.ui.profile.updateprofile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityUpdateProfileBinding
import com.trq.muslimapp.helpers.SharedPreference
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File


class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding
    lateinit var sharedPreference: SharedPreference

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val IMAGE = "image"
        const val CAMERA_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Update Profile")
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPreference = SharedPreference(this)
        val user = sharedPreference.getUser()
        val userId = user?.id


        binding.edtEmail.setText(intent.getStringExtra(EMAIL))
        binding.edtName.setText(intent.getStringExtra(NAME))
        binding.userId.text = intent.getStringExtra(ID)

        val imgUrl =
            "http://muslim.app.tiorisnanto.com/storage/${intent.getStringExtra(IMAGE)}"

        Glide.with(this)
            .load(imgUrl)
            .error(R.drawable.user)
            .into(binding.imgUser)


        binding.imgUser.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST)
        }

        binding.btnUpdateProfile.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val image = binding.imgUser.setImageBitmap(
                (binding.imgUser.drawable as BitmapDrawable).bitmap
            )

            ApiConfigRt.instanceRetrofit.updateProfile(
                userId!!.toInt(),
                name,
                email,
                image.toString()
            ).enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@UpdateProfileActivity,
                            "Update Success",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent = Intent(this@UpdateProfileActivity, UpdateProfileActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@UpdateProfileActivity,
                            "Update Failed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(this@UpdateProfileActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun convertImage(): String? {
        val bitmap = (binding.imgUser.drawable as BitmapDrawable).bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return encoded
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            binding.imgUser.setImageBitmap(bitmap)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}