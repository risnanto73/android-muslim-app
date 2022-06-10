package com.trq.muslimapp.ui.home.zakat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.FragmentZakatDagangBinding
import com.trq.muslimapp.rt.api.PiApiConfig
import com.trq.muslimapp.rt.model.ResponseEmas
import kotlinx.android.synthetic.main.fragment_zakat_dagang.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class ZakatDagangFragment : Fragment() {

    private lateinit var binding: FragmentZakatDagangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZakatDagangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PiApiConfig.getApiService().getAllEmas().enqueue(object : Callback<ResponseEmas> {
            override fun onResponse(call: Call<ResponseEmas>, response: Response<ResponseEmas>) {
                if (response.isSuccessful) {
                    val data = response.body()?.emas?.get(0)
                    binding.hargaEmas.text = formatNumber(data?.hargaemas!!.toInt())
                    binding.hargaTanggal.text = data.createdAt
                }
            }
            override fun onFailure(call: Call<ResponseEmas>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

        binding.btnHtung.setOnClickListener {
            val nisab = binding.hargaEmas.text.toString().toInt()
            var isEmpty = false

            val modal = binding.modalDagang.text.toString().trim()
            val untung = binding.keuntunganDagang.text.toString().trim()
            val piutang = binding.piutangDagang.text.toString().trim()
            val utang = binding.tempoPiutang.text.toString().trim()
            val kerugian = binding.kerugianDagang.text.toString().trim()



            if (modal.isEmpty()) {
                isEmpty = true
                binding.modalDagang.error = "Modal tidak boleh kosong"
            }

            if (untung.isEmpty()) {
                isEmpty = true
                binding.keuntunganDagang.error = "Keuntungan tidak boleh kosong"
            }

            if (piutang.isEmpty()) {
                isEmpty = true
                binding.piutangDagang.error = "Piutang tidak boleh kosong"
            }

            if (utang.isEmpty()) {
                isEmpty = true
                binding.tempoPiutang.error = "Tempo Piutang tidak boleh kosong"
            }

            if (kerugian.isEmpty()) {
                isEmpty = true
                binding.kerugianDagang.error = "Kerugian tidak boleh kosong"
            }

            if (!isEmpty) {
                val totalModal = modal.toInt() + untung.toInt() + piutang.toInt()
                val todalRugi = kerugian.toInt() + utang.toInt()
                val total =
                    modal.toInt() + untung.toInt() + piutang.toInt() - (kerugian.toInt() + utang.toInt())
                val zakat = total * 0.025

                if (total > nisab) {
                    binding.txtHasil.text =
                        "Zakat anda adalah ${formatNumber(zakat.toInt())} selama pertahun dan Wajib Zakat"
                } else {
                    binding.txtHasil.text =
                        "Zakat anda adalah ${formatNumber(zakat.toInt())} selama pertahun dan Tidak Wajib Zakat, tetapi bisa berinfaq"
                }
            }
        }


    }

    fun formatNumber(number: Int): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number)
    }

}