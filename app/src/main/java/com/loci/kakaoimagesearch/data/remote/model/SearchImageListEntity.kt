package com.loci.kakaoimagesearch.data.remote.model

import java.util.Date

data class SearchImageListEntity(val items: List<SearchImageEntity>)

data class SearchImageEntity(
    override val uuid: String,
    override val searchType: String,
    override val thumbnailUrl: String,
    override val title: String,
    override val datetime: Date,
    override val isLiked: Boolean = false
) : TotalEntity