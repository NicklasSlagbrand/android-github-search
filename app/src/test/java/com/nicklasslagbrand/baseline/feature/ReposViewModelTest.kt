package com.nicklasslagbrand.baseline.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.domain.result.Result
import com.nicklasslagbrand.baseline.feature.repo.ReposViewModel
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
import org.amshove.kluent.mock
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

    private val viewModel: ReposViewModel by inject()

    private lateinit var eventObserver: TestObserver<ConsumableEvent<Event>>
    private var reposObserver: Observer<PagedList<GithubRepo>> = mock()
    private val getRepos = mockk<GithubRepository>(relaxed = true)

    @Test
    fun `check viewmodel handles happy case correctly`() = runBlockingTest {
        coEvery {
            getRepos.getAndroidRepos(1)
        } answers {
            Result.success(listOf(testRepo))
        }
        viewModel.getReposList().observeForever(reposObserver)
        assert(viewModel.getReposList().value == listOf(testRepo))
    }

    @Test
    fun `check viewmodel handles item click correctly`() = runBlockingTest {
        coEvery {
            getRepos.getAndroidRepos(1)
        } answers {
            Result.success(listOf(testRepo))
        }
        viewModel.itemClicked(testRepo)
        eventObserver.shouldContainEvents(Event.ShowRepoDetails(testRepo))
    }

    @Test
    fun `viewmodel returning missing network error correctly`() = runBlockingTest {

        coEvery {
            getRepos.getAndroidRepos(1)
        } answers {
            Result.failure(Error.MissingNetworkConnection)
        }

        viewModel.getReposList().observeForever(reposObserver)
        eventObserver.shouldContainEvents(Event.OnError(Error.MissingNetworkConnection))
    }

    @Test
    fun `viewmodel returning GeneralError correctly`() = runBlockingTest {
        val exception = Exception()
        coEvery {
            getRepos.getAndroidRepos(1)
        } answers {
            Result.failure(Error.GeneralError(exception))
        }

        viewModel.getReposList().observeForever(reposObserver)
        eventObserver.shouldContainEvents(Event.OnError(Error.GeneralError(exception)))
    }

    @Before
    fun setUp() {
        clearAllMocks()

        startKoin(overridesModule = module(override = true) {
            single { getRepos }
        })
        eventObserver = viewModel.eventLiveData.testObserver()
    }
}
