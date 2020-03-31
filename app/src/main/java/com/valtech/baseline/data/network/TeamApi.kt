package com.valtech.baseline.data.network

import com.valtech.baseline.domain.model.Member
import retrofit2.http.GET

interface TeamApi {
    @GET("/team/members")
    suspend fun getTeamMembers(): List<Member>
}
