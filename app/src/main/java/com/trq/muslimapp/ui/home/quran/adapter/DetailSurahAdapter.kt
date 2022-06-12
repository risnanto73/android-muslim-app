package com.trq.muslimapp.ui.home.quran.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.quran.model.ModelAyat
import kotlinx.android.synthetic.main.activity_detail_surah.view.*
import kotlinx.android.synthetic.main.item_row_detail_surah.view.*
import java.util.ArrayList

class DetailSurahAdapter : RecyclerView.Adapter<DetailSurahAdapter.ViewHolder>() {
    private val modelAyatList = ArrayList<ModelAyat>()

    fun setAdapter(items: ArrayList<ModelAyat>) {
        modelAyatList.clear()
        modelAyatList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_detail_surah, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelAyatList[position]

        holder.tvNomorAyat.text = data.nomor
        holder.tvArabic.text = data.arab
        holder.tvTerjemahan.text = data.indo

    }

    override fun getItemCount(): Int {
        return modelAyatList.size
    }

    //Class Holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNomorAyat: TextView
        var tvArabic: TextView
        var tvTerjemahan: TextView

        init {
            tvNomorAyat = itemView.tvVerseId
            tvArabic = itemView.tvArabic
            tvTerjemahan = itemView.tvTerjemahan
        }
    }
}