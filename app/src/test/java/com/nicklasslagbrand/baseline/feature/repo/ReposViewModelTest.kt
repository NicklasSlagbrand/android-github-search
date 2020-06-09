package com.nicklasslagbrand.baseline.feature.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nicklasslagbrand.baseline.core.functional.Result
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.baseline.domain.usecase.UseCase
import com.nicklasslagbrand.baseline.feature.repo.ReposViewModel.Event
import com.nicklasslagbrand.baseline.testRepo
import com.nicklasslagbrand.baseline.testutils.CoroutinesMainDispatcherRule
import com.nicklasslagbrand.baseline.testutils.TestObserver
import com.nicklasslagbrand.baseline.testutils.startKoin
import com.nicklasslagbrand.baseline.testutils.testObserver
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

    private val reposViewModel: ReposViewModel by inject()

    private lateinit var eventObserver: TestObserver<ConsumableEvent<Event>>
    private lateinit var failureObserver: TestObserver<ConsumableEvent<Error>>
    private val getRepos = mockk<GetRepoListUseCase>(relaxed = true)

    @Test
    fun `check viewmodel handles happy case correctly`() = runBlockingTest {
        coEvery {
            getRepos.call(UseCase.None)
        } answers {
            Result.success(listOf(testRepo))
        }

        reposViewModel.initialize()
        eventObserver.shouldContainEvents(Event.ShowRepos(listOf(testRepo)))
    }

    @Test
    fun `check viewmodel handles failure case correctly`() = runBlockingTest {

        coEvery {
            getRepos.call(UseCase.None)
        } answers {
            Result.failure(Error.MissingNetworkConnection)
        }

        reposViewModel.initialize()
        eventObserver.shouldContainEvents(Error.MissingNetworkConnection)
    }

    @Before
    fun setUp() {
        clearAllMocks()

        startKoin(overridesModule = module(override = true) {
            single { getRepos }
        })
        eventObserver = reposViewModel.eventsLiveData.testObserver()
        failureObserver = reposViewModel.failure.testObserver()
    }
}
