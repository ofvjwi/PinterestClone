package com.example.pinterestrealniyclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterestrealniyclone.R

class RecyclerViewUpdateAdapter(private val context: Context, private val list: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.item_recycler_view_update, parent, false)
        return MyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView)
}