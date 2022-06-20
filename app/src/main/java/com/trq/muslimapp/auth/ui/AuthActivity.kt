package com.trq.muslimapp.auth.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trq.muslimapp.auth.adapter.AuthAdapter
import com.trq.muslimapp.databinding.ActivityAuthBinding
import com.trq.muslimapp.helpers.SharedPreference

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    lateinit var sharedPreference: SharedPreference
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        sharedPreference = SharedPreference(this)

        setupTabs()
        //setContentView(R.layout.activity_login)
    }

    private fun setupTabs() {
        val adapter = AuthAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(), "Login")
        adapter.addFragment(RegisterFragment(), "Register")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }


}