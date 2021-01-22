package com.nicklasslagbrand.core.repository

import com.nicklasslagbrand.core.entity.GithubRepo
import com.nicklasslagbrand.core.network.ApiService
import com.nicklasslagbrand.core.result.wrapResult
import com.nicklasslagbrand.core.result.Result


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
