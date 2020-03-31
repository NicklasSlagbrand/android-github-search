package com.valtech.baseline.data.network

import com.valtech.baseline.domain.error.NoNetworkConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val networkConnectionChecker: NetworkConnectionChecker) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (networkConnectionChecker.isNotConnected) {
            throw NoNetworkConnectionException()
        }
        return chain.proceed(chain.request())
    }
}
