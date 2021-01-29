package com.nicklasslagbrand.core

import com.nicklasslagbrand.core.testUtils.successFromFile
import com.nicklasslagbrand.core.network.ApiService
import com.nicklasslagbrand.core.testUtils.TestNetworkConnectionChecker
import com.nicklasslagbrand.core.testUtils.init
import io.mockk.clearAllMocks
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test

class ApiServiceTest {
    private val mockWebServer = MockWebServer()
    private lateinit var apiService: ApiService

    @Test(expected = retrofit2.HttpException::class)
    fun `check repository returns error when network request fails`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(500))
            coEvery {
                apiService.searchRepos("",1,1)
            } returns testSearchResponse

            apiService.searchRepos("",1,1)
        }
    }

    @Test
    fun `check repository fetches repos from network correctly`() {
        runBlocking {
            mockWebServer.enqueue(successFromFile("get-repo-list-success.json"))

            val repo = apiService.searchRepos("", 1, 1)
            assert(repo == testSearchResponse)
        }
    }

    @Before
    fun setUp() {
        clearAllMocks()

        val mockedBaseUrl = mockWebServer.init()
        val networkConnectionChecker = TestNetworkConnectionChecker(true)

        apiService = ApiService.createFakeApi( mockedBaseUrl, networkConnectionChecker )
    }
}
