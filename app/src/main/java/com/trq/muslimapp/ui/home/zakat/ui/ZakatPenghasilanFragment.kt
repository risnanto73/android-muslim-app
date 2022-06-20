package com.trq.muslimapp.ui.home.zakat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.FragmentZakatPenghasilanBinding
import com.trq.muslimapp.ui.home.zakat.ZakatActivity
import com.trq.muslimapp.ui.home.zakat.model.ResponseHargaEmas
import kotlinx.android.synthetic.main.fragment_zakat_penghasilan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
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
            var isEmpty = false
            
            val penghasilan = binding.penghasilanPokok.text.toString().trim()
            val pendapatanLain = binding.pendapatanLain.text.toString().trim()
            val pengeluaran = binding.pengeluaranPokok.text.toString().trim()

            if (penghasilan.isEmpty()) {
                binding.penghasilanPokok.error = "Penghasilan pokok tidak boleh kosong"
                isEmpty = true
            }
            
            if (pendapatanLain.isEmpty()) {
                binding.pendapatanLain.error = "Pendapatan lain tidak boleh kosong"
                isEmpty = true
            }
            
            if (pengeluaran.isEmpty()) {
                binding.pengeluaranPokok.error = "Pengeluaran pokok tidak boleh kosong"
                isEmpty = true
            }
            
            if (!isEmpty){
                val penghasilanInt = penghasilan.toInt()
                val pendapatanLainInt = pendapatanLain.toInt()
                val pengeluaranInt = pengeluaran.toInt()

                val total = (penghasilanInt + pendapatanLainInt) - pengeluaranInt

                val zakat = (penghasilanInt + pendapatanLainInt - pengeluaranInt) * 0.025

                if (total <= nisab) {
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