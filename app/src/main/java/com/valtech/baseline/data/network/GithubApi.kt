package com.valtech.baseline.data.network

import com.valtech.baseline.domain.model.GithubRepo
import retrofit2.http.GET

interface GithubApi {
    @GET("/orgs/android/repos")
    suspend fun getTeamMembers(): List<GithubRepo>
}
