package com.nicklasslagbrand.baseline.feature.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import com.nicklasslagbrand.baseline.domain.dataSource.ReposDataSource
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import kotlinx.coroutines.CoroutineDispatcher

class ReposViewModel(
    private val repository: GithubRepository,
    private val backgroundDispatcher: CoroutineDispatcher
) : ViewModel() {
    val eventLiveData = MutableLiveData<ConsumableEvent<Event>>()
    val reposLiveData: LiveData<PagedList<GithubRepo>>

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
                    repository = repository,
                    coroutineContext = backgroundDispatcher
                )
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    fun getReposList(): LiveData<PagedList<GithubRepo>> = reposLiveData

    fun itemClicked(item: GithubRepo) {
        eventLiveData.value = ConsumableEvent(Event.ShowRepoDetails(item))
    }
    sealed class Event {
        data class ShowRepoDetails(val repo: GithubRepo) : Event()
    }
}
