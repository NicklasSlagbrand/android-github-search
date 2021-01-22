package com.nicklasslagbrand.baseline.core.di

import com.nicklasslagbrand.baseline.data.network.ApiService
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.feature.repo.RepoViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidPlatformModule = module {
    single { AndroidNetworkConnectionChecker(get()) as NetworkConnectionChecker }
}

fun networkModule(baseUrl: String) = module {
    single { ApiService.create(baseUrl = baseUrl, get()) }
    single { GithubRepository(get()) }
}

val viewModelModule = module {
    viewModel { RepoViewModel(state = get(), repository = get()) }
}
