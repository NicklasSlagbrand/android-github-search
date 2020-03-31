package com.valtech.baseline.domain.usecase

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.model.Member
import com.valtech.baseline.domain.repository.TeamMemberRepository

class GetTeamMembersUseCase(private val teamMembersRepository: TeamMemberRepository) :
    UseCase<List<Member>, UseCase.None>() {
    override suspend fun call(params: None): Result<List<Member>, Error> =
            teamMembersRepository.getAllTeamMembers()
}
