package com.trq.muslimapp.ui.home.doaharian.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.doaharian.model.DzikirItem

class DoaHarianAdapter(val doaHarian: List<DzikirItem?>?) :
    RecyclerView.Adapter<DoaHarianAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtNamaDoa = view?.findViewById<TextView>(R.id.txtNamaDoa)
        val txtIsiDoa = view?.findViewById<TextView>(R.id.tvDoa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_doa_harian, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtNamaDoa?.text = doaHarian?.get(position)?.judul
//        holder.txtIsiDoa?.text = doaHarian?.get(position)?.isi

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) holder.txtIsiDoa?.text =
            Html.fromHtml(doaHarian?.get(position)?.isi, Html.FROM_HTML_MODE_COMPACT) else {
            holder.txtIsiDoa.text = Html.fromHtml(doaHarian?.get(position)?.isi)
        }
    }

    override fun getItemCount(): Int {
        return doaHarian?.size ?: 0
    }

}