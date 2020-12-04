package com.nicklasslagbrand.baseline.data.datasource

import com.nicklasslagbrand.baseline.data.network.GithubApi
import com.nicklasslagbrand.baseline.domain.model.GithubRepo

class RemoteGithubStore(
    private val githubApi: GithubApi
) {
    suspend fun getAndroidRepos(page: Long): List<GithubRepo> {
        return githubApi.getAndroidRepos(page)
    }
}