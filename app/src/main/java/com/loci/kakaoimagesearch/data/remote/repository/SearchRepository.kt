package com.loci.kakaoimagesearch.data.remote.repository

import com.loci.kakaoimagesearch.data.remote.model.SearchImageListEntity

interface SearchRepository {
    suspend fun getSearchImageList(query: String, page: Int): SearchImageListEntity?
}