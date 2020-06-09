package com.nicklasslagbrand.baseline.data.network

import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import retrofit2.http.GET

interface GithubApi {
    @GET("/orgs/android/repos")
    suspend fun getTeamMembers(): List<GithubRepo>
}
