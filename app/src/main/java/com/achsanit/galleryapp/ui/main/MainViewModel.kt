package com.achsanit.galleryapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achsanit.core.data.Repository
import com.achsanit.core.model.PhotoItemEntity
import com.achsanit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var _photos: MutableLiveData<Resource<List<PhotoItemEntity>>> =
        MutableLiveData(Resource.Loading())
    val photos: LiveData<Resource<List<PhotoItemEntity>>> get() = _photos

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            _photos.postValue(repository.getPhotos())
        }
    }

}