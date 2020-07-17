package com.nicklasslagbrand.baseline.domain.repository

import com.nicklasslagbrand.baseline.data.datasource.remote.RemoteGithubReposRepository
import com.nicklasslagbrand.baseline.testRepo
import com.nicklasslagbrand.baseline.testutils.init
import com.nicklasslagbrand.baseline.testutils.startKoin
import com.nicklasslagbrand.baseline.testutils.successFromFile
import io.mockk.clearAllMocks
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldContain
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class RepoListRepositoryTest : AutoCloseKoinTest() {
    private val mockWebServer = MockWebServer()
    private val remotesRepository: RemoteGithubReposRepository by inject()

    @Test(expected = retrofit2.HttpException::class)
    fun `check repository returns error if network failure happens`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(500))
            coEvery {
                remotesRepository.getAndroidRepos(1)
            } returns emptyList()

            remotesRepository.getAndroidRepos(1)
        }
    }

    @Test
    fun `check repository fetches repos from network correctly`() {
        runBlocking {
            mockWebServer.enqueue(successFromFile("get-repo-list-success.json"))

            val repo = remotesRepository.getAndroidRepos(1)
            repo.shouldContain(testRepo)
        }
    }

    @Before
    fun setUp() {
        clearAllMocks()

        val mockedBaseUrl = mockWebServer.init()
        startKoin(
            baseUrl = mockedBaseUrl,
            networkLogging = true,
            overridesModule = module(override = true) {
            })
    }
}
