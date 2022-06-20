package com.trq.muslimapp.ui.home.zakat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentZakatEmasBinding
import com.trq.muslimapp.ui.home.zakat.model.ResponseHargaEmas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

class ZakatEmasFragment : Fragment() {

    private lateinit var binding : FragmentZakatEmasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZakatEmasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiConfigRt.instanceRetrofit.getEmas().enqueue(object : Callback<ResponseHargaEmas> {
            override fun onResponse(
                call: Call<ResponseHargaEmas>,
                response: Response<ResponseHargaEmas>
            ) {
                if (response.isSuccessful) {
                    val hargaEmas = response.body()!!.emas?.get(0)
                    binding.hargaEmas.text =
                        "Harga Emas ${formatNumber(hargaEmas!!.hargaemas!!.toInt())}/gram pertanggal ${
                            DateFormat.getDateTimeInstance().format(Date())
                        }"

                    binding.hargaEmasHide.text = hargaEmas.hargaemas
                }
            }

            override fun onFailure(call: Call<ResponseHargaEmas>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

        binding.btnHitung.setOnClickListener {
            val nisab = binding.hargaEmasHide.text.toString().toInt() * 85

            val gold = binding.edtEmas.text.toString().trim()
            var isEmpty = false

            if (gold.isEmpty()) {
                binding.edtEmas.error = "Silahkan isi jumlah emas"
                isEmpty = true
            }

            if (!isEmpty) {
                val zakat = gold.toInt() * 0.025

                if (gold > nisab.toString()){
                    binding.txtHasil.text = "Zakat Emas Anda adalah Rp. ${formatNumber(zakat.toInt())} dan Wajib Zakat"
                } else {
                    binding.txtHasil.text =
                        "Zakat Emas Anda adalah Rp. ${formatNumber(zakat.toInt())} dan tidak Wajib Zakat, tetapi bisa berinfaq"
                }
            }
        }
    }

    private fun formatNumber(number: Int): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number)
    }

}