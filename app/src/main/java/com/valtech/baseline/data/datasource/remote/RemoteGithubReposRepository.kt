package com.valtech.baseline.data.datasource.remote

import com.valtech.baseline.data.network.GithubApi
import com.valtech.baseline.domain.model.GithubRepo

class RemoteGithubReposRepository(
    private val githubApi: GithubApi
) {
    suspend fun getTeamMembers(): List<GithubRepo> {
        return githubApi.getTeamMembers()
    }
}
