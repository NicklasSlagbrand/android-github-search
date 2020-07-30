package com.nicklasslagbrand.baseline.domain.usecase

import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.domain.result.Result
import com.nicklasslagbrand.baseline.testutils.failIfError
import com.nicklasslagbrand.baseline.testutils.startKoin
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEmpty
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class GetRepoListUseCaseTest : AutoCloseKoinTest() {
    private val useCase: GetRepoListUseCase by inject()
    private val repository = mockk<GithubRepository>()

    @Test
    fun `test use case works as expected`() {
        runBlocking {
            coEvery {
                repository.getAndroidRepos(1)
            } returns Result.success(emptyList())

            val result = useCase.call(PagingParams(1))

            result.fold({
                it.shouldBeEmpty()
            }, ::failIfError)
        }
    }

    @Before
    fun setUp() {
        clearAllMocks()

        startKoin(overridesModule = module(override = true) {
            single { repository }
        })
    }
}
