package com.nicklasslagbrand.baseline.core.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.nicklasslagbrand.baseline.data.datasource.local.LocalGithubRepoRepository
import com.nicklasslagbrand.baseline.data.datasource.local.PreferenceStorage
import com.nicklasslagbrand.baseline.data.datasource.remote.RemoteGithubReposRepository
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.nicklasslagbrand.baseline.data.network.createTeamApi
import com.nicklasslagbrand.baseline.data.time.AndroidTimeHandler
import com.nicklasslagbrand.baseline.domain.TimeHandler
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import com.nicklasslagbrand.baseline.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.baseline.domain.usecase.StoreRepoListUseCase
import com.nicklasslagbrand.baseline.feature.repo.ReposViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun androidPlatformModule() = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) as SharedPreferences }
    single { AndroidTimeHandler() as TimeHandler }
    single { AndroidNetworkConnectionChecker(get()) as NetworkConnectionChecker }
    single { Dispatchers.IO }
}

fun generalAppModule(baseUrl: String, networkLogging: Boolean) = module {
    single {
        createTeamApi(
            debug = networkLogging,
            baseUrl = baseUrl,
            connectionChecker = get()
        )
    }
    single { PreferenceStorage(get()) }

    single { LocalGithubRepoRepository() }
    single { RemoteGithubReposRepository(get()) }
    single { GithubRepository(get(), get()) }
}

fun useCaseAndViewModelModule() = module {
    factory {
        GetRepoListUseCase(get())
    }
    factory {
        StoreRepoListUseCase(get())
    }

    viewModel { ReposViewModel(get(), get(), get()) }
}
