package com.nicklasslagbrand.baseline.domain.repository

import com.nicklasslagbrand.baseline.core.functional.Result
import com.nicklasslagbrand.baseline.core.functional.wrapResult
import com.nicklasslagbrand.baseline.data.datasource.local.LocalGithubRepoRepository
import com.nicklasslagbrand.baseline.data.datasource.remote.RemoteGithubReposRepository
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo

class GithubRepository(
    private val localRepository: LocalGithubRepoRepository,
    private val remoteRepository: RemoteGithubReposRepository
) {
    suspend fun getAllTeamMembers(): Result<List<GithubRepo>, Error> {
        return wrapResult {
            remoteRepository.getTeamMembers().also {
                localRepository.storeRepoList(it)
            }
        }
    }

    fun storeAllTeamMembers(githubRepos: List<GithubRepo>): Result<Any, Error> {
        return wrapResult {
            return@wrapResult localRepository.storeRepoList(githubRepos)
        }
    }
}
