package com.nicklasslagbrand.baseline.data.network

import com.nicklasslagbrand.baseline.domain.error.NoNetworkConnectionException
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
