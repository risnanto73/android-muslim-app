package com.trq.muslimapp.ui.home.headline.adapter

import android.content.Intent
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.headline.model.Berita
import com.trq.muslimapp.ui.home.headline.model.Khutbah
import com.trq.muslimapp.ui.home.headline.model.Result
import com.trq.muslimapp.ui.home.khutbah.DetailKhutbahActivity

class KhutbahBeritaAdapter(val resulKhutbah: Khutbah?) :
    RecyclerView.Adapter<KhutbahBeritaAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tag = view.findViewById<TextView>(R.id.txtHeadline)
        val judulKhutbah = view.findViewById<TextView>(R.id.txt_judul_berita)
        val isiKhutbah = view.findViewById<TextView>(R.id.txt_isi_berita)

//        val tagBerita = view.findViewById<TextView>(R.id.txtHeadline)
//        val judulBerita = view.findViewById<TextView>(R.id.txt_judul_berita)
//        val isiBerita = view.findViewById<TextView>(R.id.txt_isi_berita)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_berita, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = resulKhutbah?.data?.get(position)
        holder.tag.text = data?.tag
        holder.judulKhutbah.text = data?.judul
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.isiKhutbah.text = Html.fromHtml(data?.isi, Html.FROM_HTML_MODE_COMPACT)
        } else {
            holder.isiKhutbah.text = Html.fromHtml(data?.isi)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailKhutbahActivity::class.java)
            intent.putExtra("judul", data?.judul)
            intent.putExtra("isi", data?.isi)
            intent.putExtra("pemateri", data?.pemateri)
            holder.itemView.context.startActivity(intent)
        }


//        val dataBerita = resultBerita?.data?.get(position)
//        holder.tagBerita.text = dataBerita?.tag
//        holder.judulBerita.text = dataBerita?.judul
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            holder.isiBerita.text = Html.fromHtml(dataBerita?.isi, Html.FROM_HTML_MODE_COMPACT)
//        } else {
//            holder.isiBerita.text = Html.fromHtml(dataBerita?.isi)
//        }
    }

    override fun getItemCount(): Int {
        return resulKhutbah?.data?.size ?: 0
    }

}