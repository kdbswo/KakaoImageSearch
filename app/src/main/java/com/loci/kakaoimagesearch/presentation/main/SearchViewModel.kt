package com.loci.kakaoimagesearch.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.repository.SearchRepository
import com.loci.kakaoimagesearch.data.remote.repository.SearchRepositoryImpl
import com.loci.kakaoimagesearch.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _getSearchImageList: MutableLiveData<List<SearchImageEntity>> = MutableLiveData()
    val getSearchImageList: LiveData<List<SearchImageEntity>> get() = _getSearchImageList

    fun getSearchImageList(query: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _getSearchImageList.value = searchRepository.getSearchImageList(query, 1)?.items
            Log.d("get", searchRepository.getSearchImageList(query, 1)?.items.toString())
        }
    }

    fun returnSearchItem(index: Int): SearchImageEntity? {
        val list = getSearchImageList.value
        return list?.get(index)
    }

    fun updateIsLike(index: Int) {
        val list = getSearchImageList.value
        list?.let {
            val updatedList = it.toMutableList()
            val updatedItem = list[index].copy(isLiked = !updatedList[index].isLiked)
            updatedList[index] = updatedItem
            Log.d("update", updatedList[index].isLiked.toString())
            _getSearchImageList.value = updatedList
        }
    }

    fun removeLike(data: SearchImageEntity){
        val currentList = _getSearchImageList.value?.toMutableList() ?: mutableListOf()
        val index = currentList.indexOf(data)
        val currentItem = data.copy(isLiked = false)
        currentList[index] = currentItem
        _getSearchImageList.value = currentList
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
