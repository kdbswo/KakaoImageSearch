package com.loci.kakaoimagesearch.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class KakaoSearchClipListResponse(
    @SerializedName("documents") val documents: List<ClipDocument>?,
    @SerializedName("meta") val meta: ClipMeta?
)


data class ClipMeta(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("pageable_count") val pageableCount: Int?,
    @SerializedName("is_end") val isEnd: Boolean?
)

data class ClipDocument(
    @SerializedName("title") val title: String?,
    @SerializedName("url") val clipUrl: String?, //영상 URL
    @SerializedName("datetime") val datetime: Date, /* 문서 작성시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz] */
    @SerializedName("play_time") val playTime: Int?, //플레이 타임
    @SerializedName("thumbnail") val thumbnailUrl: String?, //미리보기 이미지 URL
    @SerializedName("author") val author: String?, // 동영상 업로더

)