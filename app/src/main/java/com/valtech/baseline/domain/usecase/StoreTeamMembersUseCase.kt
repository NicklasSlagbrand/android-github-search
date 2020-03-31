package com.valtech.baseline.domain.usecase

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.domain.repository.GithubRepository

class StoreTeamMembersUseCase(private val teamMembersRepository: GithubRepository) :
    UseCase<Any, List<GithubRepo>>() {
    override suspend fun call(params: List<GithubRepo>): Result<Any, Error> =
            teamMembersRepository.storeAllTeamMembers(params)
}
