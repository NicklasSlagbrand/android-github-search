package com.valtech.baseline.core.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.valtech.baseline.data.datasource.local.LocalGithubRepoRepository
import com.valtech.baseline.data.datasource.local.PreferenceStorage
import com.valtech.baseline.data.datasource.remote.RemoteGithubReposRepository
import com.valtech.baseline.data.network.NetworkConnectionChecker
import com.valtech.baseline.data.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.valtech.baseline.data.network.createTeamApi
import com.valtech.baseline.data.time.AndroidTimeHandler
import com.valtech.baseline.domain.TimeHandler
import com.valtech.baseline.domain.repository.GithubRepository
import com.valtech.baseline.domain.usecase.GetRepoListUseCase
import com.valtech.baseline.domain.usecase.StoreRepoListUseCase
import com.valtech.baseline.feature.repo.ReposViewModel
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
