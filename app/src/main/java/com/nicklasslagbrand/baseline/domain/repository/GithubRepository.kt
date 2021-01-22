package com.nicklasslagbrand.baseline.domain.repository

import com.nicklasslagbrand.baseline.data.network.ApiService
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.result.Result
import com.nicklasslagbrand.baseline.domain.result.wrapResult

class GithubRepository(
    private val apiService: ApiService,
){

    suspend fun getGithubReposBySearch(
        query: String,
        lastRequestedPage: Int
    ): Result<List<GithubRepo>> = wrapResult {
        apiService.searchRepos(query, lastRequestedPage, NETWORK_PAGE_SIZE).items
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
