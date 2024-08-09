package com.loci.kakaoimagesearch.data.remote.model

import java.util.Date

data class SearchClipListEntity(val items: List<SearchClipEntity>)

data class SearchClipEntity(
    override val uuid: String,
    override val searchType: String,
    override val title: String,
    override val thumbnailUrl: String,
    override val datetime: Date,
    override val isLiked: Boolean = false
) : TotalEntity