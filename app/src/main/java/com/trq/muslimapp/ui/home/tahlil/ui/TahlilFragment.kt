package com.trq.muslimapp.ui.home.tahlil.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.FragmentTahlilBinding
import com.trq.muslimapp.ui.api.ApiConfiguration
import com.trq.muslimapp.ui.home.tahlil.adapter.TahlilsAdapter
import com.trq.muslimapp.ui.home.tahlil.model.ResponseTahlil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TahlilFragment : Fragment() {

    private lateinit var binding : FragmentTahlilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTahlilBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_tahlil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTahlil()
    }

    private fun setupTahlil() {

        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        progressDialog.show()

        val recTahlil = activity?.findViewById<RecyclerView>(R.id.rec_tahlil)
        ApiConfiguration.getApiInterface().getTahlil().enqueue(object : Callback<ResponseTahlil>{
            override fun onResponse(
                call: Call<ResponseTahlil>,
                response: Response<ResponseTahlil>
            ) {
                if (response.isSuccessful){
                    progressDialog.dismiss()
                    val responseTahlil = response.body()
                    val dataTahlil = responseTahlil?.data
                    val tahlilAdapter = TahlilsAdapter(dataTahlil)
                    recTahlil?.apply {
                        layoutManager = LinearLayoutManager(activity)
                        setHasFixedSize(true)
                        tahlilAdapter.notifyDataSetChanged()
                        adapter = tahlilAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTahlil>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}