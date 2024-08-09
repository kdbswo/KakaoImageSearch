package com.loci.kakaoimagesearch.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loci.kakaoimagesearch.data.remote.model.SearchClipEntity
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.model.TotalEntity
import com.loci.kakaoimagesearch.data.remote.repository.SearchRepository
import com.loci.kakaoimagesearch.data.remote.repository.SearchRepositoryImpl
import com.loci.kakaoimagesearch.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _getSearchImageList: MutableLiveData<List<TotalEntity>> = MutableLiveData()
    val getSearchImageList: LiveData<List<TotalEntity>> get() = _getSearchImageList

    fun getSearchImageList(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val imageList = searchRepository.getSearchImageList(query, 1)?.items
            val clipList = searchRepository.getSearchClipList(query, 1)?.items

            val totalList: MutableList<TotalEntity> = mutableListOf()
            imageList?.let { totalList.addAll(it) }
            clipList?.let { totalList.addAll(it) }
            Log.d("get", searchRepository.getSearchImageList(query, 1)?.items.toString())

           totalList.sortBy { it.datetime }
            _getSearchImageList.value = totalList
        }
    }


    fun getSearchClipList(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _getSearchImageList.value = searchRepository.getSearchClipList(query, 1)?.items
        }
    }

    fun returnSearchItem(index: Int): TotalEntity? {
        val list = getSearchImageList.value
        return list?.get(index)
    }

    fun updateIsLike(index: Int) {
        val list = getSearchImageList.value
        list?.let {
            val updatedList = it.toMutableList()
            if (list[index] is SearchImageEntity) {
                val item = list[index] as SearchImageEntity
                val imageUpdatedItem = item.copy(isLiked = !item.isLiked)
                updatedList[index] = imageUpdatedItem
            } else if (list[index] is SearchClipEntity) {
                val item = list[index] as SearchClipEntity
                val imageUpdatedItem = item.copy(isLiked = !item.isLiked)
                updatedList[index] = imageUpdatedItem
            }
            _getSearchImageList.value = updatedList
        }
    }

    fun removeLike(removeUuid: String) {
        val currentList = getSearchImageList.value?.toMutableList() ?: mutableListOf()
        val removeIndex = currentList.indexOfFirst { it.uuid == removeUuid }
        if (removeIndex != -1) {
            _getSearchImageList.value = currentList
        }


    }

    fun getPosition(data: SearchImageEntity): Int? {
        val list = getSearchImageList.value
        val index = list?.indexOf(data)
        return index
    }

}

class SearchViewModelFactory : ViewModelProvider.Factory {
    private val repository = SearchRepositoryImpl(RetrofitClient.searchImageList)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}
