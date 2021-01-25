package com.nicklasslagbrand.core

import com.nicklasslagbrand.core.error.Error
import com.nicklasslagbrand.core.network.ApiService
import com.nicklasslagbrand.core.repository.GithubRepository
import com.nicklasslagbrand.core.result.Result
import com.nicklasslagbrand.core.testUtils.*
import io.mockk.clearAllMocks
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class GithubRepositoryTest {
    private val mockWebServer = MockWebServer()
    private lateinit var apiService: ApiService
    private lateinit var repository: GithubRepository

    @Test
    fun `check repository returns correct error when request return 500`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(500))

            when(val result = repository.getGithubReposBySearch("", 1)){
                is Result.Failure -> result.error.shouldBeInstanceOf<Error.GeneralError>()
                else -> failIfSuccess(result)
            }
        }
    }

    @Test
    fun `check repository fetches repos from network correctly`() {
        runBlocking {
            mockWebServer.enqueue(successFromFile("get-repo-list-success.json"))
            when(val repo = repository.getGithubReposBySearch("", 1)) {
                is Result.Success -> {
                    assertTrue {
                        with(repo.data[0]) {
                            id == testRepo.id
                            name == testRepo.name
                            fullName == testRepo.fullName
                            description == testRepo.description
                            stars == testRepo.stars
                            forks == testRepo.forks
                            owner.avatarUrl == testOwner.avatarUrl
                            language == null
                        }
                    }
                }
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
