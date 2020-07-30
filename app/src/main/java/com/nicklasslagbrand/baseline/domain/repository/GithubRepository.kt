package com.nicklasslagbrand.baseline.domain.repository

import com.nicklasslagbrand.baseline.data.datasource.local.LocalGithubRepoRepository
import com.nicklasslagbrand.baseline.data.datasource.remote.RemoteGithubReposRepository
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.result.Result
import com.nicklasslagbrand.baseline.domain.result.wrapResult

class GithubRepository(
    private val localRepository: LocalGithubRepoRepository,
    private val remoteRepository: RemoteGithubReposRepository
) {
    suspend fun getAndroidRepos(page: Long): Result<List<GithubRepo>, Error> {
        return wrapResult {
            remoteRepository.getAndroidRepos(page).also {
                localRepository.storeRepoList(it)
            }
        }
    }
}
