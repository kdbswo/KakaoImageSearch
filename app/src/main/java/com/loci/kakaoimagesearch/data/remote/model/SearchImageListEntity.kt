package com.loci.kakaoimagesearch.data.remote.model

import java.util.Date

data class SearchImageListEntity(val items: List<SearchImageEntity>)

data class SearchImageEntity(
    val uuid: String,
    val searchType: String,
    val thumbnailUrl: String,
    val displaySitName: String,
    val datetime: Date,
    val isLiked: Boolean = false
)