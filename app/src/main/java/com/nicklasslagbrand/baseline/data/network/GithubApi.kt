package com.nicklasslagbrand.baseline.data.network

import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/orgs/android/repos")
    suspend fun getAndroidRepos(
        @Query("page") page: Long,
        @Query("per_page") pageSize: Long = 10
    ): List<GithubRepo>
}
