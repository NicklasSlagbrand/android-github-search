package com.nicklasslagbrand.baseline.domain.repository

import com.nicklasslagbrand.baseline.data.datasource.remote.RemoteGithubStore
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.result.Result
import com.nicklasslagbrand.baseline.domain.result.wrapResult

class GithubRepository(
    private val remoteRepository: RemoteGithubStore
) {
    suspend fun getAndroidRepos(page: Long): Result<List<GithubRepo>, Error> {
        return wrapResult {
            remoteRepository.getAndroidRepos(page)
        }
    }
}
