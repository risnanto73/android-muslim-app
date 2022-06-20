package com.trq.muslimapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.trq.muslimapp.MainActivity
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.ui.AuthActivity
import com.trq.muslimapp.databinding.FragmentProfileBinding
import com.trq.muslimapp.helpers.SharedPreference

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    lateinit var sharedPreference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreference = SharedPreference(requireActivity())

        val user = sharedPreference.getUser()
        if (user != null) {

            val urlImage = "http://muslim.app.tiorisnanto.com/storage/${user.gambar}"
            if (user.gambar != null) {
                Glide.with(requireActivity()).load(urlImage).into(binding.imgUser)
                binding.edit.visibility = View.VISIBLE
                binding.add.visibility = View.GONE
            } else if (user.gambar == null) {
                Glide.with(this).load("https://ui-avatars.com/api/?name=${user.name}")
                    .into(binding.imgUser)
                binding.edit.visibility = View.GONE
                binding.add.visibility = View.VISIBLE
            } else {
                Glide.with(this).load(R.drawable.user).into(binding.imgUser)
                binding.edit.visibility = View.GONE
                binding.add.visibility = View.GONE
            }

            binding.txtEmail.text = user!!.email
            binding.txtName.text = user.name
            binding.btnLogout.visibility = View.VISIBLE
        } else {
            binding.txtEmail.setText(" ")
            binding.txtName.setText(" ")
            binding.btnLogin.visibility = View.VISIBLE
            binding.btnLogin.setOnClickListener {
                startActivity(Intent(requireActivity(), AuthActivity::class.java))
            }
            binding.btnLogout.visibility = View.GONE
        }


        binding.btnLogout.setOnClickListener {
            sharedPreference.setStatusLogin(false)
            sharedPreference.deleteUser()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            Toast.makeText(requireActivity(), "Berhasi Logout", Toast.LENGTH_SHORT).show()
            requireActivity().finish()

        }
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}