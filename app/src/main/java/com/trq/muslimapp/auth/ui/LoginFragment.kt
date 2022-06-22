package com.trq.muslimapp.auth.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trq.muslimapp.MainActivity
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentLoginBinding
import com.trq.muslimapp.helpers.SharedPreference
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    lateinit var sharedPreferences: SharedPreference
    val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context)
    }

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreferences = SharedPreference(requireActivity())

        binding.btnLogin.setOnClickListener {
            login()
        }



        return binding.root
    }

    private fun login() {

        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        if (email.isEmpty() && password.isEmpty() ){
            binding.edtEmail.error = "Email is required"
            binding.edtPassword.error = "Password is required"
        }

        progressDialog.setTitle("Login")
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        ApiConfigRt.instanceRetrofit.login(email, password).enqueue(object : retrofit2.Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                val response = response.body()
                progressDialog.dismiss()
                if (response != null) {
                    if (response.status == 0) {
                        Toast.makeText(activity, "Pastikan data benar", Toast.LENGTH_SHORT).show()
                    } else if (response.status == 1) {
                        sharedPreferences.setStatusLogin(true)
                        sharedPreferences.setUser(response.user!!)
                        startActivity(Intent(activity, MainActivity::class.java))
                        activity?.finish()
                        Toast.makeText(activity, "${response.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }



}