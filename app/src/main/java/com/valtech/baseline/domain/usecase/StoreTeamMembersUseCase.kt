package com.valtech.baseline.domain.usecase

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.model.Member
import com.valtech.baseline.domain.repository.TeamMemberRepository

class StoreTeamMembersUseCase(private val teamMembersRepository: TeamMemberRepository) :
    UseCase<Any, List<Member>>() {
    override suspend fun call(params: List<Member>): Result<Any, Error> =
            teamMembersRepository.storeAllTeamMembers(params)
}
