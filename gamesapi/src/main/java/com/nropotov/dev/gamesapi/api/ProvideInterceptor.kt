package com.nropotov.dev.gamesapi.api

import okhttp3.Interceptor
import okhttp3.Response

class ProvideInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().url
            .newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(originalRequest)
            .build()

        return chain.proceed(newRequest)
    }
}