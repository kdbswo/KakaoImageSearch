package com.loci.kakaoimagesearch.network

import com.loci.kakaoimagesearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK %s".format(BuildConfig.KAKAO_IMAGE_API_KEY)
            ).build()
        return chain.proceed(newRequest)
    }
}