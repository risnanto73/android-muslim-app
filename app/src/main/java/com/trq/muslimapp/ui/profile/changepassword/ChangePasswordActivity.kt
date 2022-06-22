package com.trq.muslimapp.ui.profile.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityChangePasswordBinding
import com.trq.muslimapp.helpers.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Change Password"

        sharedPreference = SharedPreference(this)
        val user = sharedPreference.getUser()
        val userId = user?.id

        binding.userId.text = userId.toString()

        binding.btnChangePassword.setOnClickListener {
            setupChangePassword()
        }
    }

    private fun setupChangePassword() {

        val user = sharedPreference.getUser()
        val userId = user?.id

        var old = binding.etOldPassword.text.toString()
        var new = binding.etNewPassword.text.toString()
        var confirm = binding.etConfirmPassword.text.toString()

        if (old.isEmpty() || new.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else if (new != confirm) {
            Toast.makeText(
                this,
                "New password and confirm password must be same",
                Toast.LENGTH_SHORT
            ).show()
        } else if (old == new) {
            Toast.makeText(
                this,
                "Old password and new password must be different",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            userId.let {
                ApiConfigRt.instanceRetrofit.editPass(userId!!, old, new)
                    .enqueue(object : Callback<ResponseUser> {
                        override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                            Toast.makeText(
                                this@ChangePasswordActivity,
                                t.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<ResponseUser>,
                            response: Response<ResponseUser>
                        ) {
                            if (response.isSuccessful) {
                                if (response.body()?.status == 1) {
                                    Toast.makeText(
                                        this@ChangePasswordActivity,
                                        "Password changed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@ChangePasswordActivity,
                                        response.body()?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@ChangePasswordActivity,
                                    response.message(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}