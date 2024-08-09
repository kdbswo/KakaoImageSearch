package com.loci.kakaoimagesearch.data.remote.model

import java.util.Date

data class SearchClipListEntity(val items: List<SearchClipEntity>)

data class SearchClipEntity(
    val uuid: String,
    val searchType: String,
    val title: String,
    val thumbnailUrl: String,
    val datetime: Date,
    val isLiked: Boolean = false
)