package com.loci.kakaoimagesearch.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.repository.GalleryRepositoryImpl

class GalleryViewModel : ViewModel() {
    private val _galleryList: MutableLiveData<List<SearchImageEntity>> = MutableLiveData()
    val galleryList: LiveData<List<SearchImageEntity>> get() = _galleryList


    fun setGalleryList(list: List<SearchImageEntity>) {
        _galleryList.value = list
    }

    fun addGalleryList(gallery: SearchImageEntity) {
        val currentList = _galleryList.value?.toMutableList() ?: mutableListOf()
        currentList.add(gallery)
        _galleryList.value = currentList
    }

    fun removeGallery(gallery: SearchImageEntity) {
        val currentList = _galleryList.value?.toMutableList() ?: mutableListOf()
        currentList.remove(gallery)
        _galleryList.value = currentList
    }


}

class GalleryViewModelFactory : ViewModelProvider.Factory {
    private val repository = GalleryRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryViewModel() as T
    }
}