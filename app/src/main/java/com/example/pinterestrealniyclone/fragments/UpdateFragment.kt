package com.example.pinterestrealniyclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.adapters.RecyclerViewUpdateAdapter

class UpdateFragment : BaseFragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_update, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_update)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = RecyclerViewUpdateAdapter(requireContext(), prepareLists())
        recyclerView.adapter = adapter


        return rootView
    }

    private fun prepareLists(): ArrayList<Any> {
        val list = ArrayList<Any>()
        for (i in 1..15)
            list.add(1)
        return list
    }
}