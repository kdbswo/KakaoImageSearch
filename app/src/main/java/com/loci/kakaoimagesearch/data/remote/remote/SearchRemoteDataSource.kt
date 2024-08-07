package com.loci.kakaoimagesearch.data.remote.remote

import com.loci.kakaoimagesearch.data.remote.model.KakaoSearchImageListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDataSource {
    @GET("/v2/search/image")
    suspend fun getSearchImageList(
        @Query("query") query: String,
        @Query("sort") sort: String = "recency",
        @Query("page") page: Int,
        @Query("size") size: Int = 80
    ): KakaoSearchImageListResponse
}