package com.nicklasslagbrand.feature.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicklasslagbrand.core.result.Result
import com.nicklasslagbrand.core.entity.GithubRepo
import com.nicklasslagbrand.core.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel (
    private val repository: GithubRepository,
    ) : ViewModel() {
    val repoLiveData = MutableLiveData<List<GithubRepo>>()

    fun getResult(query: String){
        return loadRepos(query)
    }

    private fun loadRepos(query: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
               repository.getGithubReposBySearch(query, 0)
            }
            when (result) {
                is Result.Success -> repoLiveData.value = result.data
                is Result.Failure -> {
                }
            }
        }
    }
}
