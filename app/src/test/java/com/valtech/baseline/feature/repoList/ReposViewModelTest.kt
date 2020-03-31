package com.valtech.baseline.feature.repoList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.usecase.GetTeamMembersUseCase
import com.valtech.baseline.domain.usecase.UseCase
import com.valtech.baseline.feature.repoList.ReposViewModel.Event
import com.valtech.baseline.testMember
import com.valtech.baseline.testutils.CoroutinesMainDispatcherRule
import com.valtech.baseline.testutils.startKoin
import com.valtech.baseline.testutils.testObserver
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
class ReposViewModelTest : AutoCloseKoinTest() {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @get:Rule
    var coroutinesTestRule = CoroutinesMainDispatcherRule()

    private val getTeamMembers = mockk<GetTeamMembersUseCase>()
    private val reposViewModel: ReposViewModel by inject()

    @Test
    fun `check viewmodel handles happy case correctly`() = runBlockingTest {
        val testObserver = reposViewModel.eventsLiveData.testObserver()

        coEvery {
            getTeamMembers.call(UseCase.None)
        } answers {
            Result.success(listOf(testMember))
        }

        reposViewModel.initialize()

        testObserver.shouldContainEvents(Event.ShowTeam(listOf(testMember)))
    }

    @Test
    fun `check viewmodel handles failure case correctly`() = runBlockingTest {
        val testObserver = reposViewModel.failure.testObserver()

        coEvery {
            getTeamMembers.call(UseCase.None)
        } answers {
            Result.failure(Error.MissingNetworkConnection)
        }

        reposViewModel.initialize()

        testObserver.shouldContainEvents(Error.MissingNetworkConnection)
    }

    @Before
    fun setUp() {
        clearAllMocks()

        startKoin(overridesModule = module(override = true) {
            single { getTeamMembers }
        })
    }
}
