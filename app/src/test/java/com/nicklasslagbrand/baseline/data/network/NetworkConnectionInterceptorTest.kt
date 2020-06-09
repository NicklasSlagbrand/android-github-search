package com.nicklasslagbrand.baseline.data.network

import com.nicklasslagbrand.baseline.core.extension.empty
import com.nicklasslagbrand.baseline.domain.error.NoNetworkConnectionException
import com.nicklasslagbrand.baseline.testutils.TestNetworkConnectionChecker
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.Interceptor
import okhttp3.Request
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.Test

class NetworkConnectionInterceptorTest {
    private val networkConnectionChecker = TestNetworkConnectionChecker()
    private val interceptor = NetworkConnectionInterceptor(networkConnectionChecker)

    @Test
    fun `test interceptor throws exception when network is not available`() {
        networkConnectionChecker.isConnected = false
        val testChain = mockk<Interceptor.Chain>()

        val throwingFunction = {
            interceptor.intercept(testChain)
        }

        throwingFunction
            .shouldThrow(NoNetworkConnectionException::class)
            .withMessage(String.empty())
    }

    @Test
    fun `test interceptor processes request when network is available`() {
        networkConnectionChecker.isConnected = true

        val testChain = mockk<Interceptor.Chain>()
        // Catch request passed into Chain.proceed(request) method.
        val argument = slot<Request>()
        // Simple empty request.
        val request = mockk<Request>()
        every { testChain.request() }.returns(request)
        every { testChain.proceed(capture(argument)) }.returns(mockk())

        interceptor.intercept(testChain)
    }
}
