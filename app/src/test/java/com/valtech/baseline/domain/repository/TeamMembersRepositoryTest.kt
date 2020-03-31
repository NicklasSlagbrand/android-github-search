package com.valtech.baseline.domain.repository

import com.valtech.baseline.data.datasource.remote.RemoteGithubReposRepository
import com.valtech.baseline.testMember
import com.valtech.baseline.testutils.init
import com.valtech.baseline.testutils.startKoin
import com.valtech.baseline.testutils.successFromFile
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

class TeamMembersRepositoryTest : AutoCloseKoinTest() {
    private val mockWebServer = MockWebServer()
    private val remotesRepository: RemoteGithubReposRepository by inject()

    @Test(expected = retrofit2.HttpException::class)
    fun `check repository returns error if network failure happens`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(500))
            coEvery {
                remotesRepository.getTeamMembers()
            } returns emptyList()

            remotesRepository.getTeamMembers()
        }
    }

    @Test
    fun `check repository fetches members from network correctly`() {
        runBlocking {
            mockWebServer.enqueue(successFromFile("get-team-members-success.json"))

            val members = remotesRepository.getTeamMembers()
            members.shouldContain(testMember)
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
