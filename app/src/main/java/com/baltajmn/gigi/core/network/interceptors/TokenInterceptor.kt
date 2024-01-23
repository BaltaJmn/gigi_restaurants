package com.baltajmn.gigi.core.network.interceptors

import com.baltajmn.gigi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {

    companion object {
        private const val TOKEN_KEY = "key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader(TOKEN_KEY, BuildConfig.API_KEY)
            .url(original.url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}