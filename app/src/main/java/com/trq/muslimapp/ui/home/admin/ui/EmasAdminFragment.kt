package com.trq.muslimapp.ui.home.admin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentEmasAdminBinding
import com.trq.muslimapp.ui.home.admin.adapter.EmasAdapter
import com.trq.muslimapp.ui.home.zakat.model.ResponseHargaEmas
import kotlinx.android.synthetic.main.note_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmasAdminFragment : Fragment() {

    private lateinit var binding : FragmentEmasAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmasAdminBinding.inflate(inflater, container, false)
        return binding.root


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_emas_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            ApiConfigRt.instanceRetrofit.getEmas().enqueue(object : Callback<ResponseHargaEmas>{
                override fun onResponse(
                    call: Call<ResponseHargaEmas>,
                    response: Response<ResponseHargaEmas>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()?.emas
                        val emasAdapter = EmasAdapter(data)
                        val recEmas = view.findViewById<RecyclerView>(R.id.rv_emas)
                        recEmas.apply {
                            setHasFixedSize(true)
                            emasAdapter.notifyDataSetChanged()
                            layoutManager = LinearLayoutManager(requireActivity())
                            adapter = emasAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseHargaEmas>, t: Throwable) {
                    Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })

            swipeRefreshLayout.isRefreshing = false
        }
    }

}