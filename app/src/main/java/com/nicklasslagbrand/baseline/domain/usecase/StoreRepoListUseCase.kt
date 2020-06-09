package com.nicklasslagbrand.baseline.domain.usecase

import com.nicklasslagbrand.baseline.core.functional.Result
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository

class StoreRepoListUseCase(private val teamMembersRepository: GithubRepository) :
    UseCase<Any, List<GithubRepo>>() {
    override suspend fun call(params: List<GithubRepo>): Result<Any, Error> =
            teamMembersRepository.storeAllTeamMembers(params)
}
