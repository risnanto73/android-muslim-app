package com.trq.muslimapp.ui.home.quran.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.quran.DetailSurahActivity
import com.trq.muslimapp.ui.home.quran.model.ModelSurah
import kotlinx.android.synthetic.main.item_row_surah.view.*
import java.util.ArrayList

class SurahAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SurahAdapter.ViewHolder>() {
    private val modelSurahList = ArrayList<ModelSurah>()

    fun setAdapter(items: ArrayList<ModelSurah>) {
        modelSurahList.clear()
        modelSurahList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_surah, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelSurahList[position]

        holder.tvNumber.text = data.nomor
        holder.tvName.text = data.nama
        holder.tvAsma.text = data.asma
        holder.tvAyat.text = """
            ${data.arti} (${data.ayat.toString()} Ayat) 
        """.trimIndent()


        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailSurahActivity::class.java)
            intent.putExtra(DetailSurahActivity.DETAIL_SURAH, modelSurahList[position])
            mContext.startActivity(intent)
        }
//        holder.tvInfo.text = data.type + " - " + data.ayat + " Ayat "
//
//        holder.cvSurah.setOnClickListener {
//            val intent = Intent(mContext, DetailActivity::class.java)
//            intent.putExtra(DetailActivity.DETAIL_SURAH, modelSurahList[position])
//            mContext.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return modelSurahList.size
    }

    //Class Holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvNumber: TextView
        var tvAyat: TextView
        var tvName: TextView
        var tvAsma: TextView

        init {
            tvNumber = itemView.tvVerseId
            tvAyat = itemView.tvTerjemahan
            tvName = itemView.tv_nama_surat
            tvAsma = itemView.tvArabic
        }
    }
}