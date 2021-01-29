package com.nicklasslagbrand.core

import com.nicklasslagbrand.core.network.ApiService
import com.nicklasslagbrand.core.repository.GithubRepository
import com.nicklasslagbrand.core.result.Result
import com.nicklasslagbrand.core.testUtils.*
import io.mockk.clearAllMocks
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test

class GithubRepositoryTest {
    private val mockWebServer = MockWebServer()
    private lateinit var apiService: ApiService
    private lateinit var repository: GithubRepository

    @Test
    fun `check repository returns correct error when request return 500`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(500))

            when(val result = repository.getGithubReposBySearch("", 1)){
                is Result.Failure -> doNothingForFail(result.exception)
                else -> failIfSuccess(result)
            }
        }
    }

    @Test
    fun `check repository fetches repos from network correctly`() {
        runBlocking {
            mockWebServer.enqueue(successFromFile("get-repo-list-success.json"))
            when(val repo = repository.getGithubReposBySearch("", 1)) {
                is Result.Success -> assert(repo.data[0] == testRepo)
                is Result.Failure -> failIfException(repo.exception)
            }

        }
    }

    @Before
    fun setUp() {
        clearAllMocks()

        val mockedBaseUrl = mockWebServer.init()
        val networkConnectionChecker = TestNetworkConnectionChecker(true)

        apiService = ApiService.createFakeApi( mockedBaseUrl, networkConnectionChecker )
        repository = GithubRepository(apiService)
    }
}
