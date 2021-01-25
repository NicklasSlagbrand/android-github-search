package com.nicklasslagbrand.baseline.di

import com.nicklasslagbrand.baseline.BuildConfig
import com.nicklasslagbrand.baseline.feature.repo.RepoViewModel
import com.nicklasslagbrand.core.Core
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val coreModule = module {
    single { Core(context = get(), isDebug = BuildConfig.DEBUG).provideGithubRepository() }
}

val viewModelModule = module {
    viewModel { RepoViewModel(state = get(), repository = get()) }
}
