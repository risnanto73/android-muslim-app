package com.trq.muslimapp.ui.profile.updateprofile

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityUpdateProfileBinding
import com.trq.muslimapp.helpers.SharedPreference
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding
    lateinit var sharedPreference: SharedPreference
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val IMAGE = "image"
        private const val REQUEST_CODE_PERMISSIONS = 10
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
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


        if (intent.getStringExtra(IMAGE) == "") {
            Glide.with(this)
                .load("https://ui-avatars.com/api/?name=${intent.getStringExtra(EMAIL)}")
                .error("https://ui-avatars.com/api/?name=${intent.getStringExtra(EMAIL)}")
                .into(binding.imgUser)
        } else if (intent.getStringExtra(IMAGE) == null) {
            Glide.with(this)
                .load("https://ui-avatars.com/api/?name=${intent.getStringExtra(EMAIL)}")
                .error("https://ui-avatars.com/api/?name=${intent.getStringExtra(EMAIL)}")
                .into(binding.imgUser)
        } else {
            Glide.with(this)
                .load(imgUrl)
                .error("https://ui-avatars.com/api/?name=${intent.getStringExtra(EMAIL)}")
                .into(binding.imgUser)
        }

        binding.btnCamera.setOnClickListener { openCamera() }
        binding.btnUpdateProfile.setOnClickListener { updateProfile() }
    }

    private fun updateProfile() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val id = binding.userId.text.toString()
            val name = binding.edtName.text.toString().toRequestBody("text/plain".toMediaType())
            val email = binding.edtEmail.text.toString().toRequestBody("text/plain".toMediaType())
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "gambar",
                file.name,
                requestImageFile
            )

            val apiConfig =
                ApiConfigRt.instanceRetrofit.updateProfile(id.toInt(), name, email, imageMultipart)
            apiConfig.enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful) {
                        val responseUser = response.body()
                        if (responseUser!!.status == 1) {
                            Toast.makeText(
                                this@UpdateProfileActivity,
                                "Berhasil Update",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(
                        this@UpdateProfileActivity,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }
    }

    private fun openCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
        }
    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            //compress file
            val file = reduceFileImage(myFile)
            getFile = file

            // file to bitmap
            val bitmap = BitmapFactory.decodeFile(myFile.absolutePath)

//            val result = rotateBitmap(
//                BitmapFactory.decodeFile(getFile?.path),
//                isBackCamera
//            )

            binding.imgUser.setImageBitmap(bitmap)
        }
    }


    override fun onRestart() {
        super.onRestart()
        binding
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}