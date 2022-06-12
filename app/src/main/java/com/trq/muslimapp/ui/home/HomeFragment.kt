package com.trq.muslimapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.trq.muslimapp.databinding.FragmentHomeBinding
import com.trq.muslimapp.ui.home.hijriyah.HijriyahActivity
import com.trq.muslimapp.ui.home.qiblah.QiblahActivity
import com.trq.muslimapp.ui.home.quran.SurahActivity
import com.trq.muslimapp.ui.home.tasbih.TasbihActivity
import com.trq.muslimapp.ui.home.zakat.ZakatActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

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
//        val textView: TextView = binding.textHome

//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}