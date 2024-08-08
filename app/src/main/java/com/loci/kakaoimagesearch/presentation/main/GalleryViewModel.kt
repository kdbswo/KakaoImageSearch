package com.loci.kakaoimagesearch.presentation.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.repository.GalleryRepositoryImpl
import com.loci.kakaoimagesearch.util.convertStringToSearchImageEntity

class GalleryViewModel(context: Context) : ViewModel() {
    private val _galleryList: MutableLiveData<List<SearchImageEntity>> = MutableLiveData()
    val galleryList: LiveData<List<SearchImageEntity>> get() = _galleryList


    fun loadGalleryList(context: Context) {
        val pref = context.getSharedPreferences("pref", 0)
        val list = pref.getString("gallery", "[]")
        val convertedList = list?.let { convertStringToSearchImageEntity(it) }
        convertedList?.let { setGalleryList(it) }

    }

    fun setGalleryList(list: List<SearchImageEntity>) {
        _galleryList.value = list
        Log.d("set", galleryList.value.toString())
    }

    fun addGalleryList(gallery: SearchImageEntity) {
        val currentList = _galleryList.value?.toMutableList() ?: mutableListOf()
        currentList.add(gallery)
        _galleryList.value = currentList
    }

    fun returnGalleryData(index: Int): SearchImageEntity? {
        val currentList = _galleryList.value
        return currentList?.getOrNull(index)
    }

    fun removeGallery(index: Int) {
        val currentList = _galleryList.value?.toMutableList() ?: mutableListOf()
        if (index in 0 until currentList.size) {
            currentList.removeAt(index)
        }

        _galleryList.value = currentList

    }


}

class GalleryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    private val repository = GalleryRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryViewModel(context) as T
    }
}