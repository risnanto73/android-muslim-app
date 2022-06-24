package com.trq.muslimapp.ui.home.admin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trq.muslimapp.R
import com.trq.muslimapp.ui.home.admin.activity.DetailUserActivity
import com.trq.muslimapp.ui.home.admin.model.DataItem

class AllUserAdapter(val dataUser: List<DataItem?>?) :
    RecyclerView.Adapter<AllUserAdapter.MyviewHolder>() {
    class MyviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUser = view?.findViewById<TextView>(R.id.txt_name)
        val txtEmail = view?.findViewById<TextView>(R.id.txt_email)
        val txtRole = view?.findViewById<TextView>(R.id.txt_role)
        val imgUser = view?.findViewById<ImageView>(R.id.img_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_all_user, parent, false)
        return MyviewHolder(view)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val dataUsers = dataUser?.get(position)
        holder.txtEmail.text = dataUsers?.email
        holder.txtUser.text = dataUsers?.name
        holder.txtRole.text = dataUsers?.role

        val imgUrl = "http://muslim.app.tiorisnanto.com/storage/${dataUsers?.gambar}"

        Glide.with(holder.itemView.context)
            .load(imgUrl)
            .error(R.drawable.user)
            .into(holder.imgUser)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.ID, dataUsers?.id.toString() )
            intent.putExtra(DetailUserActivity.EMAIL, dataUsers?.email )
            intent.putExtra(DetailUserActivity.ROLE, dataUsers?.role )
            intent.putExtra(DetailUserActivity.USER, dataUsers?.name )
            intent.putExtra(DetailUserActivity.IMAGE_USER, dataUsers?.gambar )
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return dataUser?.size ?:0
    }
}