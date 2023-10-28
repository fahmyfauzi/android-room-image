package com.csm.roomimage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.csm.roomimage.R
import com.csm.roomimage.room.models.Person

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var person = emptyList<Person>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return person.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text = person[position].firstName
        holder.itemView.findViewById<TextView>(R.id.lastName_txt).text = person[position].lastName
        holder.itemView.findViewById<ImageView>(R.id.imageView).load(person[position].profilePhoto)
    }

    fun setData(person: List<Person>){
        this.person = person
        notifyDataSetChanged()
    }
}