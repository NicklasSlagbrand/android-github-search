package com.valtech.baseline.domain.usecase

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.domain.repository.GithubRepository

class GetTeamMembersUseCase(private val teamMembersRepository: GithubRepository) :
    UseCase<List<GithubRepo>, UseCase.None>() {
    override suspend fun call(params: None): Result<List<GithubRepo>, Error> =
            teamMembersRepository.getAllTeamMembers()
}
