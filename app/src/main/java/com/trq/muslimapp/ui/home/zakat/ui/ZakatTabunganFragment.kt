package com.trq.muslimapp.ui.home.zakat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentZakatTabunganBinding
import com.trq.muslimapp.ui.home.zakat.model.ResponseHargaEmas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

class ZakatTabunganFragment : Fragment() {

    private lateinit var binding: FragmentZakatTabunganBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZakatTabunganBinding.inflate(inflater, container, false)
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

            val inputSaldo = binding.tabungan.text.toString().trim()
            val bunga = binding.bungaTabungan.text.toString().trim()

            var isEmpty = false

            if (inputSaldo.isEmpty()) {
                binding.tabungan.error = "Masukkan jumlah tabungan"
                isEmpty = true
            }

            if (bunga.isEmpty()) {
                binding.bungaTabungan.error = "Masukkan bunga tabungan"
                isEmpty = true
            }


            if (!isEmpty) {
                val total = inputSaldo.toInt() + bunga.toInt()
                val zakat = 0.025 * (total)

                if (total <= nisab) {
                    binding.txtHasil.text =
                        "Zakat anda adalah ${formatNumber(zakat.toInt())} selama pertahun dan Tidak Wajib Zakat, tetapi bisa berinfaq"
                } else {
                    binding.txtHasil.text =
                        "Zakat anda adalah ${formatNumber(zakat.toInt())} selama pertahun dan Wajib Zakat"
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