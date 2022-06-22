package com.trq.muslimapp.ui.note.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.helpers.SharedPreference
import com.trq.muslimapp.ui.note.UpdateNoteActivity
import com.trq.muslimapp.ui.note.model.DataItem
import com.trq.muslimapp.ui.note.model.MutabaahItem
import com.trq.muslimapp.ui.note.model.ResponseNote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NoteAdapter(val dataNote: List<MutabaahItem?>?, val context: Context) :
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.catatan)
        val content = view.findViewById<TextView>(R.id.content)
        val noteId = view.findViewById<TextView>(R.id.noteId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_note, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val dataNote = dataNote?.get(position)
        holder.noteId.text = dataNote?.id.toString()
        holder.title.text = dataNote?.catatan
        holder.content.text = dataNote?.deskripsi

        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateNoteActivity::class.java)
            intent.putExtra("id", dataNote?.id)
            intent.putExtra("catatan", dataNote?.catatan)
            intent.putExtra("deskripsi", dataNote?.deskripsi)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataNote?.size!!
    }


}