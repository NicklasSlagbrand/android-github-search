package com.nicklasslagbrand.baseline.core.di

import com.nicklasslagbrand.baseline.data.datasource.remote.RemoteGithubStore
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.nicklasslagbrand.baseline.data.network.createGithubApi
import com.nicklasslagbrand.baseline.data.time.AndroidTimeHandler
import com.nicklasslagbrand.baseline.domain.TimeHandler
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.feature.repo.ReposViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun androidPlatformModule() = module {
    single { AndroidTimeHandler() as TimeHandler }
    single { AndroidNetworkConnectionChecker(get()) as NetworkConnectionChecker }
    single { Dispatchers.IO }
}

fun generalAppModule(baseUrl: String, networkLogging: Boolean) = module {
    single {
        createGithubApi(
            debug = networkLogging,
            baseUrl = baseUrl,
            connectionChecker = get()
        )
    }
    single { RemoteGithubStore(get()) }
    single { GithubRepository(get()) }
}

val viewModelModule = module {
    viewModel { ReposViewModel(get(), get()) }
}
