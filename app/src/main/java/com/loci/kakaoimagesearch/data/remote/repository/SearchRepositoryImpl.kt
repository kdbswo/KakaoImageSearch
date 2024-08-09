package com.loci.kakaoimagesearch.data.remote.repository

import com.loci.kakaoimagesearch.data.remote.model.SearchClipListEntity
import com.loci.kakaoimagesearch.data.remote.model.SearchImageListEntity
import com.loci.kakaoimagesearch.data.remote.remote.SearchRemoteDataSource
import com.loci.kakaoimagesearch.presentation.mapper.toEntity

class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun getSearchImageList(query: String, page: Int): SearchImageListEntity? =
        remoteDataSource.getSearchImageList(query = query, page = page).toEntity()

    override suspend fun getSearchClipList(query: String, page: Int): SearchClipListEntity? =
        remoteDataSource.getSearchClip(query = query, page = page).toEntity()


}
