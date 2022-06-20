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
import com.trq.muslimapp.databinding.FragmentBeritaBinding
import com.trq.muslimapp.ui.home.berita.adapter.BeritaAdapter
import com.trq.muslimapp.ui.home.berita.model.ResponseBerita
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeritaFragment : Fragment() {

    private lateinit var binding : FragmentBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeritaBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_berita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiConfigRt.instanceRetrofit.getBerita().enqueue(object : Callback<ResponseBerita> {
            override fun onResponse(
                call: Call<ResponseBerita>,
                response: Response<ResponseBerita>
            ) {
                if (response.isSuccessful){
                    val responseBerita = response.body()?.berita
                    val adapterBerita = BeritaAdapter(responseBerita)
                    binding.rvBerita.apply {
                        layoutManager = LinearLayoutManager(activity)
                        setHasFixedSize(true)
                        adapterBerita.notifyDataSetChanged()
                        adapter = adapterBerita
                    }

                }
            }

            override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {
                Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }

}