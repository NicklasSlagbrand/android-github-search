package com.nicklasslagbrand.baseline.domain

import com.nicklasslagbrand.baseline.data.datasource.RemoteGithubStore
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.testutils.failIfError
import com.nicklasslagbrand.baseline.testutils.startKoin
import com.nicklasslagbrand.baseline.testRepo
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
class GithubRepositoryTest : AutoCloseKoinTest() {
    private val remoteStore = mockk<RemoteGithubStore>()
    private val repository: GithubRepository by inject()

    @Test
    fun `check repo handles happy case correctly`() = runBlockingTest {
        coEvery {
            remoteStore.getAndroidRepos(1)
        } answers {
            listOf(testRepo)
        }

        val result = repository.getAndroidRepos(1)
        result.fold(
            {
            it.shouldBeEqualTo(listOf(testRepo))
            }, ::failIfError
        )
    }

    @Before
    fun setUp() {
        clearAllMocks()

        startKoin(networkLogging = true, overridesModule = module(override = true) {
            single { remoteStore }
            })
    }
}
