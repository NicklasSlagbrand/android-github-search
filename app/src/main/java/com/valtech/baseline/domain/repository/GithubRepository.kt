package com.valtech.baseline.domain.repository

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.core.functional.wrapResult
import com.valtech.baseline.data.datasource.local.LocalGithubRepoRepository
import com.valtech.baseline.data.datasource.remote.RemoteGithubReposRepository
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.error.NoNetworkConnectionException
import com.valtech.baseline.domain.model.GithubRepo
import java.net.UnknownHostException

class GithubRepository(
    private val localRepository: LocalGithubRepoRepository,
    private val remoteRepository: RemoteGithubReposRepository
) {
    suspend fun getAllTeamMembers(): Result<List<GithubRepo>, Error> {
        return wrapResult {
            remoteRepository.getTeamMembers().also {
                localRepository.storeMembers(it)
            }
        }
    }

    fun storeAllTeamMembers(githubRepos: List<GithubRepo>): Result<Any, Error> {
        return wrapResult {
            return@wrapResult localRepository.storeMembers(githubRepos)
        }
    }
}
