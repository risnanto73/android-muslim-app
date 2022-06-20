package com.trq.muslimapp.ui.home.khutbah.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.khutbah.DetailKhutbahActivity
import com.trq.muslimapp.ui.home.khutbah.model.KhutbahItem

class KhutbahAdapter(val dataKhutbah : List<KhutbahItem?>?) : RecyclerView.Adapter<KhutbahAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPemateri = view.findViewById<TextView>(R.id.txtPemateri)
        val tvJudul = view.findViewById<TextView>(R.id.txtJudul)
        val tvTanggal = view.findViewById<TextView>(R.id.txtTanggal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_khutbah, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvPemateri.text = dataKhutbah?.get(position)?.pemateri
        holder.tvJudul.text = dataKhutbah?.get(position)?.judul
        holder.tvTanggal.text = dataKhutbah?.get(position)?.createdAt

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailKhutbahActivity::class.java)
            intent.putExtra("judul", dataKhutbah?.get(position)?.judul)
            intent.putExtra("isi", dataKhutbah?.get(position)?.isi)
            intent.putExtra("pemateri", dataKhutbah?.get(position)?.pemateri)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataKhutbah?.size ?: 0
    }
}