package com.nicklasslagbrand.baseline.domain.usecase

import com.nicklasslagbrand.baseline.core.functional.Result
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository

class GetRepoListUseCase(private val teamMembersRepository: GithubRepository) :
    UseCase<List<GithubRepo>, UseCase.None>() {
    override suspend fun call(params: None): Result<List<GithubRepo>, Error> =
            teamMembersRepository.getAllTeamMembers()
}
