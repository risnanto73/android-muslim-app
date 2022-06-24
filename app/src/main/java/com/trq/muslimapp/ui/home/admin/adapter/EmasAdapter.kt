package com.trq.muslimapp.ui.home.admin.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.ui.home.zakat.model.EmasItem
import com.trq.muslimapp.ui.home.zakat.model.ResponseHargaEmas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class EmasAdapter(val dataEmas: List<EmasItem?>?) :
    RecyclerView.Adapter<EmasAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hargaEmas = view?.findViewById<TextView>(R.id.txtEmas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_emas, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.hargaEmas.text = formatNumber(dataEmas?.get(position)?.hargaemas!!.toInt())
//        val context = holder.itemView.context

        val id = dataEmas?.get(position)?.id
        val hargaEmas = dataEmas?.get(position)?.hargaemas

        holder.itemView.setOnClickListener {
            val alertDialog = AlertDialog.Builder(holder.itemView.context).create()
            val views = LayoutInflater.from(holder.itemView.context).inflate(R.layout.edit_emas, null)
            alertDialog.setView(views)
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            val hargaEmas = views.findViewById<EditText>(R.id.txtEmas)
            val btnUpdate = views.findViewById<TextView>(R.id.btnUpdate)


            btnUpdate.setOnClickListener {
                val harga = views?.findViewById<EditText>(R.id.txtEmas)?.text.toString()
                ApiConfigRt.instanceRetrofit.editEmas(id!!.toInt(), harga.toInt()).enqueue(object : Callback<ResponseHargaEmas>{
                    override fun onResponse(
                        call: Call<ResponseHargaEmas>,
                        response: Response<ResponseHargaEmas>
                    ) {
                        val response = response.body()
                        if (response != null) {
                            if (response.status == 1){
                                Toast.makeText(holder.itemView.context, "Berhasil", Toast.LENGTH_SHORT).show()
                                alertDialog.dismiss()
                            }
                        } else{
                            Toast.makeText(holder.itemView.context, "Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseHargaEmas>, t: Throwable) {
                        Toast.makeText(holder.itemView.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                })
            }

            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return dataEmas?.size ?: 0
    }

    fun formatNumber(number: Int): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number)
    }
}