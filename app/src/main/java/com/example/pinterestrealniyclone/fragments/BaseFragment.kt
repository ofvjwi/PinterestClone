package com.example.pinterestrealniyclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pinterestrealniyclone.adapters.RecyclerViewHomeAdapter
import com.example.pinterestrealniyclone.models.photo.Photos
import com.example.pinterestrealniyclone.retrofit.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun getList(
        currentPage: Int,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        refreshLayout: SwipeRefreshLayout?
    ) {
        RetrofitHttp.retrofitService.getPhotosWithPages(currentPage)
            .enqueue(object : Callback<ArrayList<Photos>> {
                override fun onFailure(call: Call<ArrayList<Photos>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ArrayList<Photos>>,
                    response: Response<ArrayList<Photos>>
                ) {
                    response.body()?.let { (adapter as RecyclerViewHomeAdapter).refresh(it) }
                    if (refreshLayout != null) {
                        refreshLayout.isRefreshing = false
                    }
                }
            })
    }
}
