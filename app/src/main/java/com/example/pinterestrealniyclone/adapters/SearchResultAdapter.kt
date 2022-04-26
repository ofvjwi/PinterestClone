package com.example.pinterestrealniyclone.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.models.search.SearchResultsItem
import com.google.android.material.imageview.ShapeableImageView

class SearchResultAdapter(
    private val context: Context,
    private val list: ArrayList<SearchResultsItem>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.item_recycler_view_home, parent, false)
        return MyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list?.get(position)

        if (holder is MyViewHolder) {
            Glide.with(context).load(item?.urls?.small)
                .into((holder as MyViewHolder).shapableImageView)
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}

private class MyViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
    val shapableImageView: ShapeableImageView =
        myItemView.findViewById(R.id.item_recycler_view_home_image)
}


