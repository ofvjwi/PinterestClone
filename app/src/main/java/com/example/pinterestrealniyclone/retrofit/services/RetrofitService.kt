package com.example.pinterestrealniyclone.retrofit.services

import com.example.pinterestrealniyclone.models.photo.Photos
import com.example.pinterestrealniyclone.models.search.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    @Headers("Client-ID PztvxxoIp2C4oRujcX8IyYu82848vfYzy66Pqa5uLx0")

    @GET("/photos")
    fun getPhotosWithPages(
        @Query("page") currentPage: Int
    ): Call<ArrayList<Photos>>

    @GET("search/photos")
    fun getSearchResult(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Call<Search>
}


