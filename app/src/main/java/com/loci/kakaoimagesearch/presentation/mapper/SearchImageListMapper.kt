package com.loci.kakaoimagesearch.presentation.mapper

import com.google.gson.Gson
import com.loci.kakaoimagesearch.data.remote.model.ClipDocument
import com.loci.kakaoimagesearch.data.remote.model.KakaoSearchImageListResponse
import com.loci.kakaoimagesearch.data.remote.model.SearchClipEntity
import com.loci.kakaoimagesearch.data.remote.model.SearchDocument
import com.loci.kakaoimagesearch.data.remote.model.SearchImageEntity
import com.loci.kakaoimagesearch.data.remote.model.SearchImageListEntity
import java.util.UUID

fun KakaoSearchImageListResponse.toEntity() = documents?.let {
    SearchImageListEntity(
        items = it.asSearchImageEntity()
    )
}

fun List<SearchDocument>.asSearchImageEntity(): List<SearchImageEntity> {
    return map {
        SearchImageEntity(
            UUID.randomUUID().toString(),
            "Image",
            it.thumbnailUrl ?: "",
            it.displaySiteName ?: "",
            it.datetime,
            false
        )
    }
}

fun List<ClipDocument>.asSearchClipEntity(): List<SearchClipEntity> {
    return map {
        SearchClipEntity(
            UUID.randomUUID().toString(),
            "Video",
            it.title ?: "",
            it.thumbnailUrl ?: "",
            it.datetime,
            false
        )
    }
}


fun String.toSearchImageListEntity(): SearchImageListEntity {
    val gson = Gson()
    return gson.fromJson(this, SearchImageListEntity::class.java)
}