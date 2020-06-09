package com.nicklasslagbrand.baseline.data.network

import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = if (chain.request().url.toString().contains("rest/session/token")) {
            chain.request()
        } else {
            chain.request()
                .newBuilder()
                .build()
        }

        return chain.proceed(request)
    }
}
