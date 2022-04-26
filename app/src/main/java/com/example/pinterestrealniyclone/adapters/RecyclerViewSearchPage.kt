package com.example.pinterestrealniyclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.models.SearchModel
import com.google.android.material.imageview.ShapeableImageView

class RecyclerViewSearchPage(
    private val context: Context,
    private val list: ArrayList<SearchModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
            .inflate(R.layout.item_default_recycler_view_search_page, parent, false)
        return MyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]

        if (holder is MyViewHolder) {
            (holder as MyViewHolder).shapableImageView.setImageResource(item.image)
            (holder as MyViewHolder).textView.text = item.title
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        val shapableImageView: ShapeableImageView =
            myItemView.findViewById(R.id.rounded_shape_image_search_page)
        val textView: TextView = myItemView.findViewById(R.id.text_view_transparent)
    }
}