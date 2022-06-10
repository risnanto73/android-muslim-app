package com.trq.muslimapp.ui.home.tahlil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.tahlil.model.Data
import com.trq.muslimapp.ui.home.tahlil.model.IsiItem

class TahlilsAdapter(val dataTahlil: Data?) : RecyclerView.Adapter<TahlilsAdapter.MyViewHolder>(){
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtArab = view.findViewById<TextView>(R.id.txtArab)
        val txtArti = view.findViewById<TextView>(R.id.txtIndoText)
        val txtLatin = view.findViewById<TextView>(R.id.txtLatin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_tahlil, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataTahlil = dataTahlil?.isi?.get(position)
        holder.txtArab.text = dataTahlil?.text
        holder.txtArti.text = dataTahlil?.translationId
        holder.txtLatin.text = dataTahlil?.transliteration
    }

    override fun getItemCount(): Int {
        return dataTahlil?.isi?.size ?: 0
    }
}