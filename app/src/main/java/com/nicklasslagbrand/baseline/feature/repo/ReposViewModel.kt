package com.nicklasslagbrand.baseline.feature.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.baseline.domain.usecase.StoreRepoListUseCase
import com.nicklasslagbrand.baseline.domain.usecase.UseCase
import com.nicklasslagbrand.baseline.feature.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel (
    private val getRepoListUseCase: GetRepoListUseCase,
    private val storeRepoListUseCase: StoreRepoListUseCase,
    private val backgroundDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()
    lateinit var activeGithubRepo: GithubRepo

    fun initialize() {
        viewModelScope.launch {
            val result = withContext(backgroundDispatcher) {
                getRepoListUseCase.call(UseCase.None)
            }
            result.fold({
                eventsLiveData.value = ConsumableEvent(Event.ShowRepos(it))
            }, ::handleFailure)
        }
    }

    fun reposClicked(repo: GithubRepo) {
        activeGithubRepo = repo
        showRepoDetails(repo)
    }

    private fun showRepoDetails(repo: GithubRepo) {
        eventsLiveData.value = ConsumableEvent(Event.ShowRepoDetails(repo))
    }

    private fun storeMembers(list: List<GithubRepo>) {
        viewModelScope.launch {
            val result = withContext(backgroundDispatcher) {
                storeRepoListUseCase.call(list)
            }
            result.fold({
            }, ::handleFailure)
        }
    }

    sealed class Event {
        data class ShowRepos(val githubRepos: List<GithubRepo>) : Event()
        data class ShowRepoDetails(val repo: GithubRepo): Event()
    }
}