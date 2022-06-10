package com.trq.muslimapp.ui.home.quran.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.quran.model.AyahsItem
import com.trq.muslimapp.ui.home.quran.model.ResponseDetailSurah

class DetailSurahAdapter(val dataDetailSurah: List<AyahsItem?>?) : RecyclerView.Adapter<DetailSurahAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textVerse = view.findViewById<TextView>(R.id.tvVerseId)
        val tvArabic = view.findViewById<TextView>(R.id.tvArabic)
        val tvTranslate = view.findViewById<TextView>(R.id.tvTerjemahan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_detail_surah, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataDetail = dataDetailSurah?.get(position)
        holder.textVerse.text = dataDetail?.verseId.toString()
        holder.tvArabic.text = dataDetail?.ayahText
        holder.tvTranslate.text = dataDetail?.indoText

    }

    override fun getItemCount(): Int {
        return dataDetailSurah?.size!!
    }
}