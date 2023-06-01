package com.example.imageglimpse.view.network

import com.example.imageglimpse.model.ImageData
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {

    @GET("/photos")
    suspend fun getImages():Response<List<ImageData>>


}