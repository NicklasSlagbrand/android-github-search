package com.valtech.baseline.domain.usecase

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.repository.TeamMemberRepository
import com.valtech.baseline.testutils.doNothingForSuccess
import com.valtech.baseline.testutils.failIfError
import com.valtech.baseline.testutils.startKoin
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class StoreTeamMembersUseCaseTest : AutoCloseKoinTest() {
    private val useCase: StoreTeamMembersUseCase by inject()
    private val teamMembersRepository = mockk<TeamMemberRepository>()

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
