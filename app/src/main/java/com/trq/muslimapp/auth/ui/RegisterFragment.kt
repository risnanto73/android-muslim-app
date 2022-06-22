package com.trq.muslimapp.auth.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.trq.muslimapp.MainActivity
import com.trq.muslimapp.auth.model.ResponseUser
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentRegisterBinding
import com.trq.muslimapp.helpers.SharedPreference
import retrofit2.Call
import retrofit2.Response


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    lateinit var sharedPreference: SharedPreference
    val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.hide()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        sharedPreference = SharedPreference(requireActivity())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            setupRegister()
        }
    }

    private fun setupRegister() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            binding.edtName.error = "Please fill all fields"
            binding.edtEmail.error = "Please fill all fields"
            binding.edtPassword.error = "Please fill all fields"
        }

        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        ApiConfigRt.instanceRetrofit.register(name, email, password)
            .enqueue(object : retrofit2.Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    val response = response.body()
                    progressDialog.dismiss()
                    if (response != null) {
                        try {
                            if (response.status == 0) {
                                Toast.makeText(activity, response.message, Toast.LENGTH_SHORT)
                                    .show()
                            } else if (response.status == 1) {
                                sharedPreference.setStatusLogin(true)
                                sharedPreference.setUser(response.user!!)
                                startActivity(Intent(activity, MainActivity::class.java))
                                activity?.finish()
                                Toast.makeText(activity, "${response.message}", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })
    }

}