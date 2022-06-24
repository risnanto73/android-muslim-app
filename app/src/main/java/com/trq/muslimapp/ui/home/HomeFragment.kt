package com.trq.muslimapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentHomeBinding
import com.trq.muslimapp.helpers.SharedPreference
import com.trq.muslimapp.ui.home.admin.activity.AdminActivity
import com.trq.muslimapp.ui.home.hijriyah.HijriyahActivity
import com.trq.muslimapp.ui.home.doaharian.DoaHarianActivity
import com.trq.muslimapp.ui.home.headline.adapter.BeritaKhutbahAdapter
import com.trq.muslimapp.ui.home.headline.adapter.KhutbahBeritaAdapter
import com.trq.muslimapp.ui.home.headline.model.ResponseKhutbahBerita
import com.trq.muslimapp.ui.home.jadwalsholat.JadwalSholatActivity
import com.trq.muslimapp.ui.home.khutbah.KhutbahActivity
import com.trq.muslimapp.ui.home.qiblah.QiblahActivity
import com.trq.muslimapp.ui.home.quran.SurahActivity
import com.trq.muslimapp.ui.home.tasbih.TasbihActivity
import com.trq.muslimapp.ui.home.zakat.ZakatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var sharedPreference: SharedPreference

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPreference = SharedPreference(requireActivity())
        val user = sharedPreference.getUser()
        val userId = user?.id

        if (userId == 1) {
            binding.btnListUser.visibility = View.VISIBLE
            binding.btnListUser.setOnClickListener {
                startActivity(Intent(requireActivity(), AdminActivity::class.java))
            }
        } else {
            binding.btnListUser.visibility = View.GONE
        }

        val current = SimpleDateFormat("yyyy-MM-dd")
        val tanggal = current.format(Date())
        binding.tvTanggal.text = tanggal

//        if (userRole == "admin"){
//            binding.btnListUser.visibility = View.VISIBLE
//        } else {
//            binding.btnListUser.visibility = View.GONE
//        }


        binding.btnDoaHarian.setOnClickListener {
            startActivity(Intent(activity, DoaHarianActivity::class.java))
        }

        binding.btnJadwalSholat.setOnClickListener {
            val intent = Intent(activity, JadwalSholatActivity::class.java)
            startActivity(intent)
        }

        binding.btnQuran.setOnClickListener {
            val i = Intent(activity, SurahActivity::class.java)
            startActivity(i)
        }

        binding.btnZakat.setOnClickListener {
            val i = Intent(activity, ZakatActivity::class.java)
            startActivity(i)
        }

        binding.btnKalenderHijriyah.setOnClickListener {
            val i = Intent(activity, HijriyahActivity::class.java)
            startActivity(i)
        }

        binding.btnTasbih.setOnClickListener {
            startActivity(Intent(activity, TasbihActivity::class.java))
        }

        binding.btnQiblah.setOnClickListener {
            startActivity(Intent(activity, QiblahActivity::class.java))
        }

        binding.btnKhutbah.setOnClickListener {
            startActivity(Intent(activity, KhutbahActivity::class.java))
        }
//        val textView: TextView = binding.textHome

//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getKhutbah()
        getBerita()
    }

    private fun getBerita() {
        ApiConfigRt.instanceRetrofit.getBeritaDanKhutbah()
            .enqueue(object : Callback<ResponseKhutbahBerita> {
                override fun onFailure(call: Call<ResponseKhutbahBerita>, t: Throwable) {
                    Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseKhutbahBerita>,
                    response: Response<ResponseKhutbahBerita>
                ) {
                    if (response.isSuccessful) {

                        val responseBerita = response.body()
                        val dataBerita = responseBerita?.result?.berita
                        val adapterBerita = BeritaKhutbahAdapter(dataBerita)
                        val recVKhutbahBerita =
                            view?.findViewById<RecyclerView>(R.id.recyclerViewBerita)
                        recVKhutbahBerita?.apply {
                            layoutManager = LinearLayoutManager(activity)
                            setHasFixedSize(true)
                            adapterBerita.notifyDataSetChanged()
                            adapter = adapterBerita
                        }

                    }
                }

            })
    }

    private fun getKhutbah() {
        ApiConfigRt.instanceRetrofit.getBeritaDanKhutbah()
            .enqueue(object : Callback<ResponseKhutbahBerita> {
                override fun onFailure(call: Call<ResponseKhutbahBerita>, t: Throwable) {
                    Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseKhutbahBerita>,
                    response: Response<ResponseKhutbahBerita>
                ) {
                    if (response.isSuccessful) {

                        val responseBerita = response.body()
                        val dataBerita = responseBerita?.result?.berita

                        val responKhutbahBerita = response.body()
                        val dataKhutbahBerita = responKhutbahBerita?.result?.khutbah

                        val adapterKhutbahBerita = KhutbahBeritaAdapter(dataKhutbahBerita)
                        val recVKhutbahBerita =
                            view?.findViewById<RecyclerView>(R.id.recyclerViewBeritakhutbah)
                        recVKhutbahBerita?.apply {
                            layoutManager = LinearLayoutManager(activity)
                            setHasFixedSize(true)
                            adapterKhutbahBerita.notifyDataSetChanged()
                            adapter = adapterKhutbahBerita
                        }

                    }
                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}