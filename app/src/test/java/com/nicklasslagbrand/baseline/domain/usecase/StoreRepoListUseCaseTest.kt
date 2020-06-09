package com.nicklasslagbrand.baseline.domain.usecase

import com.nicklasslagbrand.baseline.core.functional.Result
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.testutils.doNothingForSuccess
import com.nicklasslagbrand.baseline.testutils.failIfError
import com.nicklasslagbrand.baseline.testutils.startKoin
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class StoreRepoListUseCaseTest : AutoCloseKoinTest() {
    private val useCase: StoreRepoListUseCase by inject()
    private val teamMembersRepository = mockk<GithubRepository>()

    @Test
    fun `test use case works as expected`() {
        runBlocking {
            coEvery {
                teamMembersRepository.storeAllTeamMembers(emptyList())
            } returns Result.success(Any())

            val result = useCase.call(emptyList())

            result.fold(::doNothingForSuccess, ::failIfError)
        }
    }

    @Before
    fun setUp() {
        clearAllMocks()

        startKoin(overridesModule = module(override = true) {
            single { teamMembersRepository }
        })
    }
}
