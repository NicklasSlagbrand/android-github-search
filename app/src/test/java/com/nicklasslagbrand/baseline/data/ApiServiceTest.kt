package com.nicklasslagbrand.baseline.data

import com.nicklasslagbrand.baseline.data.network.ApiService
import com.nicklasslagbrand.baseline.testRepo
import com.nicklasslagbrand.baseline.testSearchResponse
import com.nicklasslagbrand.baseline.testutils.init
import com.nicklasslagbrand.baseline.testutils.startKoin
import com.nicklasslagbrand.baseline.testutils.successFromFile
import io.mockk.clearAllMocks
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class ApiServiceTest : AutoCloseKoinTest() {
    private val mockWebServer = MockWebServer()
    private val apiService: ApiService by inject()

    @Test(expected = retrofit2.HttpException::class)
    fun `check repository returns error if network failure happens`() {
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

            val repo = apiService.searchRepos("",1,1)
            repo.total.shouldBeEqualTo(1122077)
        }
    }

    @Before
    fun setUp() {
        clearAllMocks()

        val mockedBaseUrl = mockWebServer.init()
        startKoin(
            baseUrl = mockedBaseUrl,
            overridesModule = module(override = true) {
            })
    }
}
