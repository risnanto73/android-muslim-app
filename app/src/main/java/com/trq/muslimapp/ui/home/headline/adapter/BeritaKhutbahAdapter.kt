package com.trq.muslimapp.ui.home.headline.adapter

import android.content.Intent
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.berita.activity.DetailBeritaActivity
import com.trq.muslimapp.ui.home.headline.model.Berita

class BeritaKhutbahAdapter(val resultBerita: Berita?) :
    RecyclerView.Adapter<BeritaKhutbahAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tagBerita = view.findViewById<TextView>(R.id.txtHeadline)
        val judulBerita = view.findViewById<TextView>(R.id.txt_judul_berita)
        val isiBerita = view.findViewById<TextView>(R.id.txt_isi_berita)
        val imgBerita = view.findViewById<ImageView>(R.id.img_berita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_berita, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataBerita = resultBerita?.data?.get(position)
        holder.tagBerita.text = dataBerita?.tag
        holder.judulBerita.text = dataBerita?.judul

        Glide.with(holder.itemView.context)
            .load("http://muslim.app.tiorisnanto.com/storage/${dataBerita?.gambar}")
            .into(holder.imgBerita)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.isiBerita.text = Html.fromHtml(dataBerita?.isi, Html.FROM_HTML_MODE_COMPACT)
        } else {
            holder.isiBerita.text = Html.fromHtml(dataBerita?.isi)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailBeritaActivity::class.java)
            intent.putExtra("judul", dataBerita?.judul)
            intent.putExtra("isi", dataBerita?.isi)
            intent.putExtra("gambar", dataBerita?.gambar)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return resultBerita?.data?.size ?: 0
    }
}