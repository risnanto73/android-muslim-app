package com.trq.muslimapp.auth.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trq.muslimapp.MainActivity
import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityLoginBinding
import com.trq.muslimapp.helpers.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var sPH: SharedPreference
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        sPH = SharedPreference(this)

        binding.btnLogin.setOnClickListener {
            setupLogin()
        }
        //setContentView(R.layout.activity_login)
    }

    private fun setupLogin() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            binding.edtEmail.error = "Email is required"
            binding.edtPassword.error = "Password is required"
            return
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.show()

        ApiConfigRt.instanceRetrofit.login(email, password)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    val respon = response.body()
                    if (respon != null) {
                        if (respon.status == 0) {
                            progressDialog.dismiss()
                            Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            progressDialog.dismiss()
                            sPH.setStatusLogin(true)
                            sPH.setUser(respon.user!!)
                            Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT)
                                .show()
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                )
                            )
                        }
                    }

                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, t.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            })

    }
}