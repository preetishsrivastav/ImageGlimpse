package com.example.imageglimpse.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imageglimpse.model.ImageData
import com.example.imageglimpse.view.network.ImageApi

class Repository(val imageApi: ImageApi) {

    private val imageMutableLiveData = MutableLiveData<List<ImageData>>()

    val imageLiveData: LiveData<List<ImageData>>
        get() = imageMutableLiveData

    suspend fun getImages() {
        val result = imageApi.getImages()

        if (result.body() != null) {
            imageMutableLiveData.postValue(result.body())
        }
    }

}