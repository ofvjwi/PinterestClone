package com.example.pinterestrealniyclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.adapters.RecyclerViewSearchPage
import com.example.pinterestrealniyclone.models.SearchModel

class SearchFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView1SearchPage: RecyclerView
    private lateinit var recyclerView2SearchPage: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView1SearchPage = rootView.findViewById(R.id.recycler_view1_search_page)
        recyclerView2SearchPage = rootView.findViewById(R.id.recycler_view2_search_page)
        searchEditText = rootView.findViewById(R.id.search_edittext)

        recyclerView1SearchPage.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView2SearchPage.layoutManager = GridLayoutManager(requireContext(), 2)


        val adapter = RecyclerViewSearchPage(requireContext(), prepareLists())

        recyclerView1SearchPage.adapter = adapter
        recyclerView2SearchPage.adapter = adapter

        searchEditText.setOnFocusChangeListener { _, _ ->
            findNavController().navigate(R.id.searchResultFragment)
        }

        return rootView
    }

    private fun prepareLists(): ArrayList<SearchModel> {
        val list = ArrayList<SearchModel>()
        for (i in 1..8) list.add(SearchModel(R.drawable.im_sample, "Мрачные места"))
        return list
    }
}