package com.trq.muslimapp.ui.home.zakat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.FragmentZakatPenghasilanBinding
import com.trq.muslimapp.ui.home.zakat.ZakatActivity
import kotlinx.android.synthetic.main.fragment_zakat_penghasilan.*
import java.text.NumberFormat
import java.util.*

class ZakatPenghasilanFragment : Fragment() {

    private lateinit var binding : FragmentZakatPenghasilanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZakatPenghasilanBinding.inflate(inflater, container, false)
        return binding.root

        val actionBar = (activity as ZakatActivity).supportActionBar
        actionBar!!.title = "Zakat Penghasilan"

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHitung.setOnClickListener {
            val nisab = 83640000

            val penghasilan = binding.penghasilanPokok.text.toString().trim()
            val pendapatanLain = binding.pendapatanLain.text.toString().trim()
            val pengeluaran = binding.pengeluaranPokok.text.toString().trim()

            if (penghasilan.isEmpty()) {
                binding.penghasilanPokok.error = "Penghasilan tidak boleh kosong"
            }

            if (pendapatanLain.isEmpty()) {
                binding.pendapatanLain.error = "Pendapatan lain tidak boleh kosong"
            }

            if (pengeluaran.isEmpty()) {
                binding.pengeluaranPokok.error = "Pengeluaran tidak boleh kosong"
            }

            if (penghasilan.isNotEmpty() && pendapatanLain.isNotEmpty() && pengeluaran.isNotEmpty()) {
                val penghasilanInt = penghasilan.toInt()
                val pendapatanLainInt = pendapatanLain.toInt()
                val pengeluaranInt = pengeluaran.toInt()

                val total = penghasilanInt + pendapatanLainInt - pengeluaranInt

                val zakat = (penghasilanInt + pendapatanLainInt - pengeluaranInt) * 0.025

                if (nisab > total) {
                    binding.txtHasil.text =
                        "Zakat anda adalah ${formatNumber(zakat.toInt())} selama pertahun dan ${
                            formatNumber(zakat.toInt() / 12)
                        } selama perbulan dan tidak wajib zakat , tetapi bisa berinfaq"
                } else {
                    binding.txtHasil.text =
                        "Zakat anda adalah ${formatNumber(zakat.toInt())} selama pertahun dan ${
                            formatNumber(zakat.toInt() / 12)
                        } selama perbulan dan wajib zakat"
                }
            }
        }

//        binding.webView.webViewClient = WebViewClient()
//        binding.webView.settings.javaScriptEnabled = true
//        binding.webView.loadUrl("https://baznas.go.id/index.php/id/zakat-penghasilan")
        //binding.webView.settings.builtInZoomControls = true

    }

    private fun formatNumber(number: Int): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number)
    }

}