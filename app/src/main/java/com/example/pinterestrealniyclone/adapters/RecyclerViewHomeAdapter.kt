package com.example.pinterestrealniyclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.models.photo.Photos
import com.google.android.material.imageview.ShapeableImageView

class RecyclerViewHomeAdapter(
    private val context: Context,
    private val list: ArrayList<Photos>,
    private val myItemClickListener: MyItemClickListener,
    private val onBottomReachedListener: OnBottomReachedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.item_recycler_view_home, parent, false)
        return MyViewHolder(layoutInflater)
    }

    fun submitList(arrayList: ArrayList<Photos>) {
        list.addAll(arrayList)
        notifyItemRangeInserted(list.size - arrayList.size, list.size - 1)
    }

    fun refresh(arrayList: ArrayList<Photos>) {
        list.clear()
        list.addAll(arrayList)
        notifyItemRangeInserted(0, list.size - 1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]

        if (position == list.size - 1) {
            onBottomReachedListener.onBottomReached()
        }

        if (holder is MyViewHolder) {

            val myViewHolder = (holder as MyViewHolder)

            Glide.with(context).load(item.urls?.small).error(R.drawable.shimmer)
                .placeholder(R.drawable.shimmer)
                .into(myViewHolder.shapableImageView).waitForLayout()

            myViewHolder.shapableImageView.setOnClickListener {
                myItemClickListener.onItemClick(item, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        val shapableImageView: ShapeableImageView =
            myItemView.findViewById(R.id.item_recycler_view_home_image)
    }

    interface MyItemClickListener {
        fun onItemClick(photos: Photos?, position: Int)
    }

    interface OnBottomReachedListener {
        fun onBottomReached()
    }
}

