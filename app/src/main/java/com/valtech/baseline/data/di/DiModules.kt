package com.valtech.baseline.data.di

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.valtech.baseline.data.datasource.local.LocalTeamMemberRepository
import com.valtech.baseline.data.datasource.local.PreferenceStorage
import com.valtech.baseline.data.datasource.remote.RemoteTeamMembersRepository
import com.valtech.baseline.data.network.NetworkConnectionChecker
import com.valtech.baseline.data.network.NetworkConnectionChecker.AndroidNetworkConnectionChecker
import com.valtech.baseline.data.network.createTeamApi
import com.valtech.baseline.data.time.AndroidTimeHandler
import com.valtech.baseline.domain.TimeHandler
import com.valtech.baseline.domain.repository.TeamMemberRepository
import com.valtech.baseline.domain.usecase.GetTeamMembersUseCase
import com.valtech.baseline.domain.usecase.StoreTeamMembersUseCase
import com.valtech.baseline.feature.login.LoginViewModel
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel
import com.valtech.baseline.feature.team.TeamMembersViewModel
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

    single { LocalTeamMemberRepository() }
    single { RemoteTeamMembersRepository(get()) }
    single { TeamMemberRepository(get(), get()) }
}

fun useCaseAndViewModelModule() = module {
    factory {
        GetTeamMembersUseCase(get())
    }
    factory {
        StoreTeamMembersUseCase(get())
    }

    viewModel { TeamMembersViewModel(get(), get(), get()) }
    viewModel { NavigationMenuViewModel() }
    viewModel { LoginViewModel() }
}
