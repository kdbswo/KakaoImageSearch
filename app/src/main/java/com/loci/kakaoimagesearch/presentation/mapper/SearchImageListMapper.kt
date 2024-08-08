package com.loci.kakaoimagesearch.presentation.mapper

import com.google.gson.Gson
import com.loci.kakaoimagesearch.data.remote.model.KakaoSearchImageListResponse
import com.loci.kakaoimagesearch.data.remote.model.SearchDocument
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.model.SearchImageListEntity

fun KakaoSearchImageListResponse.toEntity() = documents?.let {
    SearchImageListEntity(
        items = it.asSearchImageEntity()
    )
}

fun List<SearchDocument>.asSearchImageEntity(): List<SearchImageEntity> {
    return map {
        SearchImageEntity(
            it.thumbnailUrl ?: "",
            it.displaySiteName ?: "",
            it.datetime
        )
    }
}


fun String.toSearchImageListEntity(): SearchImageListEntity {
    val gson = Gson()
    return gson.fromJson(this, SearchImageListEntity::class.java)
}