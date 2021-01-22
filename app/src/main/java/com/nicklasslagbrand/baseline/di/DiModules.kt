package com.nicklasslagbrand.baseline.di

import com.nicklasslagbrand.baseline.BuildConfig
import com.nicklasslagbrand.core.network.ApiService
import com.nicklasslagbrand.core.network.NetworkConnectionChecker
import com.nicklasslagbrand.core.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.nicklasslagbrand.core.repository.GithubRepository
import com.nicklasslagbrand.baseline.feature.repo.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidPlatformModule = module {
    single { AndroidNetworkConnectionChecker(get()) as NetworkConnectionChecker }
}

fun networkModule(baseUrl: String) = module {
    single {
        ApiService.create(baseUrl = baseUrl, debug = BuildConfig.DEBUG, connectionChecker = get())
    }
    single { GithubRepository(get()) }
}

val viewModelModule = module {
    viewModel { RepoViewModel(state = get(), repository = get()) }
}
