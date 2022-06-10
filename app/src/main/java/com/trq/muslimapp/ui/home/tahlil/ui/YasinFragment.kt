package com.trq.muslimapp.ui.home.tahlil.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.FragmentYasinBinding
import com.trq.muslimapp.ui.home.quran.network.ApiConfig
import com.trq.muslimapp.ui.home.tahlil.adapter.YasinAdapter
import com.trq.muslimapp.ui.home.tahlil.model.ResponseYasin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YasinFragment : Fragment() {

    private lateinit var binding: FragmentYasinBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYasinBinding.inflate(inflater, container, false)
        return binding.root

        // return inflater.inflate(R.layout.fragment_yasin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYasinMP3.setOnClickListener {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource("http://ia802609.us.archive.org/13/items/quraninindonesia/036Yaasiin.mp3")
            mediaPlayer.prepare()
            mediaPlayer.start()
            binding.btnYasinMP3Pasue.visibility = View.VISIBLE
            binding.btnYasinMP3.visibility = View.GONE
            binding.btnYasinMP3Pasue.setOnClickListener {
                mediaPlayer.pause()
                binding.btnYasinMP3Pasue.visibility = View.GONE
                binding.btnYasinMP3.visibility = View.VISIBLE
            }
        }

        val recYasin = activity?.findViewById<RecyclerView>(R.id.rec_yasin)

        ApiConfig.getApiService().getYasin().enqueue(object : Callback<ResponseYasin> {
            override fun onResponse(call: Call<ResponseYasin>, response: Response<ResponseYasin>) {
                if (response.isSuccessful) {
                    val responseYasin = response.body()
                    val dataYasin = responseYasin?.ayahs
                    val yasinAdapter = YasinAdapter(dataYasin)
                    recYasin?.apply {
                        layoutManager = LinearLayoutManager(activity)
                        setHasFixedSize(true)
                        yasinAdapter.notifyDataSetChanged()
                        adapter = yasinAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseYasin>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }


}