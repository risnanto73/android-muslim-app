package com.trq.muslimapp.ui.home.khutbah.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentKhutbahBinding
import com.trq.muslimapp.ui.home.khutbah.adapter.KhutbahAdapter
import com.trq.muslimapp.ui.home.khutbah.model.ResponseKhutbah
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KhutbahFragment : Fragment() {

    private lateinit var binding: FragmentKhutbahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKhutbahBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_khutbah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiConfigRt.instanceRetrofit.getKhutbah().enqueue(object: Callback<ResponseKhutbah>{
            override fun onResponse(
                call: Call<ResponseKhutbah>,
                response: Response<ResponseKhutbah>
            ) {
                if (response.isSuccessful){
                    val responseKhutbah = response.body()
                    val dataKhutbah = responseKhutbah?.khutbah
                    val khutbahAdapter = KhutbahAdapter(dataKhutbah)
                    binding.rvKhutbah.apply {
                        layoutManager = LinearLayoutManager(activity)
                        setHasFixedSize(true)
                        khutbahAdapter.notifyDataSetChanged()
                        adapter = khutbahAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseKhutbah>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }


}