package com.loci.kakaoimagesearch.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class KakaoSearchImageListResponse(
    @SerializedName("documents") val documents: List<SearchDocument>?,
    @SerializedName("meta") val meta: Meta?
)

data class Meta(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("pageable_count") val pageableCount: Int?,
    @SerializedName("is_end") val isEnd: Boolean?
)

data class SearchDocument(
    @SerializedName("collection") val collection: String?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?, //미리보기 이미지 URL
    @SerializedName("image_url") val imgUrl: String?, //이미지 URL
    @SerializedName("width") val width: Int?, //이미지의 가로 길이
    @SerializedName("height") val height: Int?, //이미지의 세로 길이
    @SerializedName("display_sitename") val displaySiteName: String?, //출처
    @SerializedName("doc_url") val docUrl: String?, //문서 URL
    @SerializedName("datetime") val datetime: Date, /* 문서 작성시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz] */

)