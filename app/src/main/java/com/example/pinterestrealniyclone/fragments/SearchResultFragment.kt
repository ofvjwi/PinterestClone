package com.example.pinterestrealniyclone.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.adapters.SearchResultAdapter
import com.example.pinterestrealniyclone.models.search.Search
import com.example.pinterestrealniyclone.retrofit.RetrofitHttp
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var textInputEditText: TextInputEditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchResultAdapter
    private var counter: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_search_result, container, false)

        textInputEditText = rootView.findViewById(R.id.search_edittext_result)
        recyclerView = rootView.findViewById(R.id.recycler_view_search_result_page)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        textInputEditText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("TAG", "onCreateViewwww: ")
                val text = textView.text.toString()
                RetrofitHttp.retrofitService.getSearchResult(text, counter++)
                    .enqueue(object : Callback<Search> {
                        override fun onResponse(
                            call: Call<Search>,
                            response: Response<Search>
                        ) {
                            Log.d("TAG", "onResponseeee: ")
                            adapter =
                                SearchResultAdapter(requireContext(), response.body()?.results)
                            recyclerView.adapter = adapter
                        }

                        override fun onFailure(call: Call<Search>, t: Throwable) {

                        }
                    })
            }

            true
        }


        return rootView
    }
}

