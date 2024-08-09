package com.loci.kakaoimagesearch.data.remote.model

import java.util.Date

interface TotalEntity {
    val uuid: String
    val title: String
    val searchType: String
    val thumbnailUrl: String
    val datetime: Date
    val isLiked: Boolean
}
