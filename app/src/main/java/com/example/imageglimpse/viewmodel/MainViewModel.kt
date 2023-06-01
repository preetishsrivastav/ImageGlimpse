package com.example.imageglimpse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageglimpse.model.ImageData
import com.example.imageglimpse.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

init {
    viewModelScope.launch(Dispatchers.IO) {
        repository.getImages()
    }

}
    val imageLiveData:LiveData<List<ImageData>>
        get() = repository.imageLiveData

}