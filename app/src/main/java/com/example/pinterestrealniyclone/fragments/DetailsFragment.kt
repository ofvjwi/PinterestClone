package com.example.pinterestrealniyclone.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.adapters.RecyclerViewHomeAdapter
import com.example.pinterestrealniyclone.models.Important
import com.example.pinterestrealniyclone.models.photo.Photos
import com.example.pinterestrealniyclone.retrofit.RetrofitHttp
import com.google.android.material.imageview.ShapeableImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var backImageArrow: ImageView
    private lateinit var mainImageDetails: ImageView
    private lateinit var profileImageDetails: ShapeableImageView
    private lateinit var profileNameDetails: TextView
    private lateinit var subscribersDetails: TextView
    private lateinit var descriptionDetails: TextView
    private lateinit var comments: TextView
    private lateinit var commentsProfileImageDetails: ShapeableImageView
    private lateinit var recyclerViewDetailsFragment: RecyclerView
    private lateinit var adapter: RecyclerViewHomeAdapter
    private var counter = 1
    private lateinit var list: ArrayList<Photos>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_details, container, false)
        initViews(rootView)

        val important = arguments?.getSerializable("KEY")
        if (important != null) {
            val data = important as Important
            Glide.with(requireContext()).load(data.imageUrl).into(mainImageDetails)
            Glide.with(requireContext()).load(data.imageUrl).into(profileImageDetails)
            Glide.with(requireContext()).load(data.imageUrl).into(commentsProfileImageDetails)
            subscribersDetails.text = data.followers.toString()
            profileNameDetails.text = data.firstName.toString()
            descriptionDetails.text = data.altDescription.toString()
            comments.text = data.description.toString()
            list = ArrayList()

            RetrofitHttp.retrofitService.getPhotosWithPages(counter++)
                .enqueue(object : Callback<ArrayList<Photos>> {
                    override fun onResponse(
                        call: Call<ArrayList<Photos>>,
                        response: Response<ArrayList<Photos>>
                    ) {
                        response.body()?.let { list.addAll(it) }
                        adapter = RecyclerViewHomeAdapter(
                            requireContext(),
                            list,
                            object : RecyclerViewHomeAdapter.MyItemClickListener {
                                override fun onItemClick(photos: Photos?, position: Int) {

                                    val datum = Important(
                                        photos?.urls?.regular,
                                        photos?.links?.followers,
                                        photos?.user?.firstName, photos?.altDescription.toString(),
                                        photos?.description.toString()
                                    )

                                    findNavController().navigate(
                                        R.id.detailsFragment,
                                        Bundle().apply {
                                            putSerializable("KEY", datum)
                                        })
                                }
                            }, object : RecyclerViewHomeAdapter.OnBottomReachedListener {
                                override fun onBottomReached() {

                                    RetrofitHttp.retrofitService.getPhotosWithPages(
                                        counter++
                                    )
                                        .enqueue(object : Callback<ArrayList<Photos>> {
                                            override fun onResponse(
                                                call: Call<ArrayList<Photos>>,
                                                response: Response<ArrayList<Photos>>
                                            ) {
                                                response.body()?.let { adapter.submitList(it) }
                                            }

                                            override fun onFailure(
                                                call: Call<ArrayList<Photos>>,
                                                t: Throwable
                                            ) {

                                            }
                                        })
                                }
                            })
                        recyclerViewDetailsFragment.adapter = adapter
                    }

                    override fun onFailure(call: Call<ArrayList<Photos>>, t: Throwable) {
                        Log.d("TAG", "onFailure: ")
                    }
                })
            recyclerViewDetailsFragment.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        }

        backImageArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        return rootView
    }

    private fun initViews(rootView: View) {
        backImageArrow = rootView.findViewById(R.id.back_image_arrow)
        mainImageDetails = rootView.findViewById(R.id.main_image_details)
        profileImageDetails = rootView.findViewById(R.id.profile_image_details)
        profileNameDetails = rootView.findViewById(R.id.profile_name_details)
        subscribersDetails = rootView.findViewById(R.id.subscribers_details)
        descriptionDetails = rootView.findViewById(R.id.description_details)
        comments = rootView.findViewById(R.id.comments_details)
        commentsProfileImageDetails = rootView.findViewById(R.id.comments_profile_image_details)
        recyclerViewDetailsFragment = rootView.findViewById(R.id.recycler_view_details_fragment)
    }
}

