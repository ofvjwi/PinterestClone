package com.example.pinterestrealniyclone.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.adapters.RecyclerViewHomeAdapter
import com.example.pinterestrealniyclone.models.Important
import com.example.pinterestrealniyclone.models.photo.Photos
import com.example.pinterestrealniyclone.retrofit.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : BaseFragment() {

    private var counter = 1
    private lateinit var rootView: View
    private lateinit var itemsList: ArrayList<Photos>
    private lateinit var adapter: RecyclerViewHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val progressBar = rootView.findViewById<ProgressBar>(R.id.progress_bar)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.home_recycler_view)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        itemsList = ArrayList()

        val refreshLayout = rootView.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)

        refreshLayout.setOnRefreshListener {
            getList(counter++, adapter, refreshLayout)
        }

        RetrofitHttp.retrofitService.getPhotosWithPages(counter++)
            .enqueue(object : Callback<ArrayList<Photos>> {
                override fun onResponse(
                    call: Call<ArrayList<Photos>>,
                    response: Response<ArrayList<Photos>>
                ) {
                    response.body()?.let { itemsList.addAll(it) }
                    adapter = RecyclerViewHomeAdapter(
                        requireContext(),
                        itemsList,
                        object : RecyclerViewHomeAdapter.MyItemClickListener {
                            override fun onItemClick(photos: Photos?, position: Int) {

                                val important = Important(
                                    photos?.urls?.regular,
                                    photos?.links?.followers,
                                    photos?.user?.firstName, photos?.altDescription.toString(),
                                    photos?.description.toString()
                                )

                                findNavController().navigate(R.id.detailsFragment, Bundle().apply {
                                    putSerializable("KEY", important)
                                })
                            }
                        }, object : RecyclerViewHomeAdapter.OnBottomReachedListener {
                            override fun onBottomReached() {
                                progressBar.visibility = View.VISIBLE

                                RetrofitHttp.retrofitService.getPhotosWithPages(counter++)
                                    .enqueue(object : Callback<ArrayList<Photos>> {
                                        override fun onResponse(
                                            call: Call<ArrayList<Photos>>,
                                            response: Response<ArrayList<Photos>>
                                        ) {
                                            Log.d("TAG", "onResponse2: ${response.body()}")
                                            response.body()?.let { adapter.submitList(it) }
                                            progressBar.visibility = View.INVISIBLE
                                        }

                                        override fun onFailure(
                                            call: Call<ArrayList<Photos>>,
                                            t: Throwable
                                        ) {

                                        }
                                    })
                            }
                        })
                    recyclerView.adapter = adapter
                }

                override fun onFailure(call: Call<ArrayList<Photos>>, t: Throwable) {
                    Log.d("TAG", "onFailure: ")
                }
            })
        return rootView
    }
}

