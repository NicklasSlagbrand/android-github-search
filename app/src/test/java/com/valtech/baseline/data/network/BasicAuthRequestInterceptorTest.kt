package com.valtech.baseline.data.network

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.Test

class BasicAuthRequestInterceptorTest {
    private val requestInterceptor = BasicAuthRequestInterceptor()

    @Test
    fun `verify BasicAuthRequestInterceptor adds basic auth header to any request`() {
        val testChain = mockk<Interceptor.Chain>()
        // Catch request passed into Chain.proceed(request) method.
        val argument = slot<Request>()
        // Simple empty request.
        val request = Request.Builder().url("http://some/url")
            .build()
        every { testChain.request() }.returns(request)
        every { testChain.proceed(capture(argument)) }.returns(mockk())

        requestInterceptor.intercept(testChain)
    }
}
