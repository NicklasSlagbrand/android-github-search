package com.nicklasslagbrand.baseline.data.network

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.Interceptor
import okhttp3.Request
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class JsonRequestInterceptorTest {
    private val requestInterceptor = JsonRequestInterceptor()

    @Test
    fun `verify JsonRequestInterceptor adds json content and accept headers to any request`() {
        val testChain = mockk<Interceptor.Chain>()
        // Catch request passed into Chain.proceed(request) method.
        val argument = slot<Request>()
        // Simple empty request.
        val request = Request.Builder().url("http://some/url")
            .build()
        every { testChain.request() }.returns(request)
        every { testChain.proceed(capture(argument)) }.returns(mockk())

        requestInterceptor.intercept(testChain)

        argument.captured.header("accept").shouldBeEqualTo("application/json")
        argument.captured.header("Content-Type").shouldBeEqualTo("application/json")
    }
}
