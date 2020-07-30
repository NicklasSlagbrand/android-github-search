package com.nicklasslagbrand.baseline.feature.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import com.nicklasslagbrand.baseline.domain.dataSource.ReposDataSource
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.baseline.feature.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class ReposViewModel(
    private val getRepoListUseCase: GetRepoListUseCase,
    private val backgroundDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()
    lateinit var activeGithubRepo: GithubRepo
    var reposLiveData: LiveData<PagedList<GithubRepo>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .build()
        reposLiveData = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Long, GithubRepo> {

        val dataSourceFactory = object : DataSource.Factory<Long, GithubRepo>() {
            override fun create(): DataSource<Long, GithubRepo> {
                return ReposDataSource(
                    errorLiveData = failure,
                    useCase = getRepoListUseCase,
                    coroutineContext = backgroundDispatcher
                )
            }
        }
        return LivePagedListBuilder<Long, GithubRepo>(dataSourceFactory, config)
    }

    fun getReposList(): LiveData<PagedList<GithubRepo>> = reposLiveData

    fun itemClicked(item: GithubRepo) {
        activeGithubRepo = item
        eventsLiveData.value = ConsumableEvent(Event.ShowRepoDetails(item))
    }
    sealed class Event {
        data class ShowRepoDetails(val repo: GithubRepo) : Event()
    }
}
