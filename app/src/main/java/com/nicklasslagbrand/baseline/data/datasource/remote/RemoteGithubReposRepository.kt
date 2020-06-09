package com.nicklasslagbrand.baseline.data.datasource.remote

import com.nicklasslagbrand.baseline.data.network.GithubApi
import com.nicklasslagbrand.baseline.domain.model.GithubRepo

class RemoteGithubReposRepository(
    private val githubApi: GithubApi
) {
    suspend fun getTeamMembers(): List<GithubRepo> {
        return githubApi.getTeamMembers()
    }
}
