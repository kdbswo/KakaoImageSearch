package com.loci.kakaoimagesearch.presentation.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.model.TotalEntity
import com.loci.kakaoimagesearch.data.remote.repository.GalleryRepositoryImpl
import com.loci.kakaoimagesearch.util.convertStringToSearchImageEntity

class GalleryViewModel(context: Context) : ViewModel() {
    private val _galleryList: MutableLiveData<List<TotalEntity>> = MutableLiveData()
    val galleryList: LiveData<List<TotalEntity>> get() = _galleryList



    fun loadGalleryList(context: Context) {
        val pref = context.getSharedPreferences("pref", 0)
        val list = pref.getString("gallery", "[]")
        val convertedList = list?.let { convertStringToSearchImageEntity(it) }
        convertedList?.let { setGalleryList(it) }

    }

    fun setGalleryList(list: List<TotalEntity>) {
        _galleryList.value = list
        Log.d("set", galleryList.value.toString())
    }

    fun addGalleryList(gallery: TotalEntity) {
        val currentList = galleryList.value?.toMutableList() ?: mutableListOf()
        currentList.add(gallery)
        _galleryList.value = currentList
    }

    fun returnGalleryData(index: Int): TotalEntity? {
        val currentList = galleryList.value
        return currentList?.getOrNull(index)
    }

    fun removeGallery(removeUuid: String) {
        val currentList = galleryList.value?.toMutableList() ?: mutableListOf()
        currentList.removeAll{it.uuid == removeUuid}
        _galleryList.value = currentList
    }

    fun includeData(data: TotalEntity): Boolean {
        val currentList = galleryList.value?.toMutableList() ?: mutableListOf()
        return currentList.contains(data)
    }


}

class GalleryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    private val repository = GalleryRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryViewModel(context) as T
    }
}