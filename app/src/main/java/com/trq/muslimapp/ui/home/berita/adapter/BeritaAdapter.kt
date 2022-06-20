package com.trq.muslimapp.ui.home.berita.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.berita.activity.DetailBeritaActivity
import com.trq.muslimapp.ui.home.berita.model.BeritaItem
import com.trq.muslimapp.ui.home.quran.DetailSurahActivity
import kotlin.coroutines.coroutineContext

class BeritaAdapter(val dataBerita: List<BeritaItem?>?) :
    RecyclerView.Adapter<BeritaAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val tag = view.findViewById<TextView>(R.id.tag)
        val judul = view.findViewById<TextView>(R.id.txt_judul_berita)
        val isiBerita = view.findViewById<TextView>(R.id.txt_isi_berita)
        val imgBerita = view.findViewById<ImageView>(R.id.img_berita)
        val kategori = view.findViewById<TextView>(R.id.txtHeadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_berita, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.judul.text = dataBerita?.get(position)?.judul
        holder.kategori.text = dataBerita?.get(position)?.tag

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.isiBerita.text =
                Html.fromHtml(dataBerita?.get(position)?.isi, Html.FROM_HTML_MODE_COMPACT)
        } else {
            holder.isiBerita.text = Html.fromHtml(dataBerita?.get(position)?.isi)
        }

        val imgUrl =
            "http://muslim.app.tiorisnanto.com/storage/${dataBerita?.get(position)?.gambar}"

        Glide.with(holder.itemView.context)
            .load(imgUrl)
            .into(holder.imgBerita)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailBeritaActivity::class.java)
            intent.putExtra("judul", dataBerita?.get(position)?.judul)
            intent.putExtra("isi", dataBerita?.get(position)?.isi)
            intent.putExtra("gambar", dataBerita?.get(position)?.gambar)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataBerita?.size ?: 0
    }
}