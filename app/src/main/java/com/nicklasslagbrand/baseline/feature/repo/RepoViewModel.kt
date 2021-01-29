package com.nicklasslagbrand.baseline.feature.repo

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nicklasslagbrand.core.repository.GithubRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class RepoViewModel(
    private val repository: GithubRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    init {
        if (!state.contains(KEY_SEARCH_QUERY)) {
            state.set(KEY_SEARCH_QUERY, DEFAULT_SEARCH_QUERY)
        }
    }

    @ExperimentalCoroutinesApi
    val githubRepos =
        state.getLiveData<String>(KEY_SEARCH_QUERY).asFlow().flatMapLatest {
            Pager(config = PagingConfig(10)) {
                RepoPagingSource(repository, it)
            }.flow.cachedIn(viewModelScope)
        }

    fun searchRepositories(query: String){
        state.set(KEY_SEARCH_QUERY, query)
    }

    companion object {
        const val KEY_SEARCH_QUERY = "searchQuery"
        const val DEFAULT_SEARCH_QUERY = "android"
    }
}
