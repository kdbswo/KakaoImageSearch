package com.loci.kakaoimagesearch.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.repository.SearchRepository
import com.loci.kakaoimagesearch.data.remote.repository.SearchRepositoryImpl
import com.loci.kakaoimagesearch.network.RetrofitClient
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _getSearchImageList: MutableLiveData<List<SearchImageEntity>> = MutableLiveData()
    val getSearchImageList: LiveData<List<SearchImageEntity>> get() = _getSearchImageList

    fun getSearchImageList() = viewModelScope.launch {
        _getSearchImageList.value = searchRepository.getSearchImageList("아이브", 1)?.items
    }

}


class SearchViewModelFactory : ViewModelProvider.Factory {
    private val repository = SearchRepositoryImpl(RetrofitClient.searchImageList)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}
