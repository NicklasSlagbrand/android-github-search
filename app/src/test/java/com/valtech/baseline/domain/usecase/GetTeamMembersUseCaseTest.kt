package com.valtech.baseline.domain.usecase

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.repository.GithubRepository
import com.valtech.baseline.testutils.failIfError
import com.valtech.baseline.testutils.startKoin
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

class GetTeamMembersUseCaseTest : AutoCloseKoinTest() {
    private val useCase: GetTeamMembersUseCase by inject()
    private val teamMembersRepository = mockk<GithubRepository>()

    @Test
    fun `test use case works as expected`() {
        runBlocking {
            coEvery {
                teamMembersRepository.getAllTeamMembers()
            } returns Result.success(emptyList())

            val result = useCase.call(UseCase.None)

            result.fold({
                it.shouldBeEmpty()
            }, ::failIfError)
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
