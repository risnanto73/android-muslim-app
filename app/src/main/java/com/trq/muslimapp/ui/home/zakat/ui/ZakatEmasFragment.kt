package com.trq.muslimapp.ui.home.zakat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.FragmentZakatEmasBinding
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
        binding.btnHitung.setOnClickListener {
            val nisab = 83640000

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