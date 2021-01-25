package com.nicklasslagbrand.core

import android.content.Context
import com.nicklasslagbrand.core.network.ApiService
import com.nicklasslagbrand.core.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.nicklasslagbrand.core.repository.GithubRepository

class Core(
    private val context: Context,
    private val isDebug: Boolean,
){
    fun provideGithubRepository(): GithubRepository {
        return GithubRepository(provideApiService())
    }

    private fun provideApiService(): ApiService =
        ApiService.create(
            debug = isDebug,
            connectionChecker = provideConnectionChecker()
     )

    private fun provideConnectionChecker() =
        AndroidNetworkConnectionChecker(context)
}