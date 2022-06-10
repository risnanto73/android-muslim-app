package com.trq.muslimapp.ui.home.tahlil.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.tahlil.model.AyahsItem

class YasinAdapter(val dataYasin: List<AyahsItem?>?) :
    RecyclerView.Adapter<YasinAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textAverseId = view.findViewById<TextView>(R.id.txtVerseId)
        val textAyahText = view.findViewById<TextView>(R.id.txtAyahText)
        val textIndoText = view.findViewById<TextView>(R.id.txtIndoText)
        val btnPlay = view.findViewById<ImageButton>(R.id.btnPlay)
        val btnPause = view.findViewById<ImageButton>(R.id.btnPause)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_yasin, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataYasin?.get(position)
        holder.textAverseId.text = data?.verseId.toString()
        holder.textAyahText.text = data?.ayahText
        holder.textIndoText.text = data?.indoText

        holder.btnPlay.setOnClickListener {
            val mp = MediaPlayer()
            mp.setDataSource(data?.audio)
            mp.prepare()
            mp.start()
            holder.btnPlay.visibility = View.GONE

            holder.btnPause.visibility = View.VISIBLE
            holder.btnPause.setOnClickListener {
                mp.stop()
                mp.reset()
                mp.release()
                holder.btnPause.visibility = View.GONE
                holder.btnPlay.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return dataYasin?.size!!
    }
}