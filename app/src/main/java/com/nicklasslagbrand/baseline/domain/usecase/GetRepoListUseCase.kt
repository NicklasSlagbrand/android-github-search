package com.nicklasslagbrand.baseline.domain.usecase

import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.domain.result.Result

class GetRepoListUseCase(private val repository: GithubRepository) :
    UseCase<List<GithubRepo>, PagingParams>() {
    override suspend fun call(params: PagingParams): Result<List<GithubRepo>, Error> =
        repository.getAndroidRepos(params.page)
}

data class PagingParams(
    val page: Long
)
