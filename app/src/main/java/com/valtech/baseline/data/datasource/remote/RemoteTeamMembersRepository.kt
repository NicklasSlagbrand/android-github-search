package com.valtech.baseline.data.datasource.remote

import com.valtech.baseline.data.network.TeamApi
import com.valtech.baseline.domain.model.Member

class RemoteTeamMembersRepository(
    private val teamApi: TeamApi
) {
    suspend fun getTeamMembers(): List<Member> {
        return teamApi.getTeamMembers()
    }
}
