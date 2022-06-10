package com.trq.muslimapp.ui.home.quran.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.quran.DetailSurahActivity
import com.trq.muslimapp.ui.home.quran.model.DataItem

class SurahAdapter(val dataSurah : List<DataItem?>?) : RecyclerView.Adapter<SurahAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textAsma = view.findViewById<TextView>(R.id.txtAsma)
        val textNumberOfAyah = view.findViewById<TextView>(R.id.txtNumberAyah)
        val textNameSurah = view.findViewById<TextView>(R.id.txtName)
        val textNumberSurah = view.findViewById<TextView>(R.id.txtNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_surah, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataSurah?.get(position)
        holder.textAsma.text = data?.asma
        holder.textNumberOfAyah.text = data?.numberOfAyahs.toString()
        holder.textNameSurah.text = data?.name
        holder.textNumberSurah.text = data?.number.toString()

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, DetailSurahActivity::class.java)
            i.putExtra("number", data?.number)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return dataSurah?.size!!
    }
}