package com.nicklasslagbrand.baseline.testutils

import com.nicklasslagbrand.baseline.core.di.networkModule
import com.nicklasslagbrand.baseline.core.di.viewModelModule
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.core.KoinApplication
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("EXPERIMENTAL_API_USAGE")
fun startKoin(
    baseUrl: String = "",
    overridesModule: Module
): KoinApplication {
    // Gather all required dependencies
    val allModules = listOf(
        networkModule(baseUrl),
        module {
            single<NetworkConnectionChecker> { TestNetworkConnectionChecker(true) }
            single<CoroutineDispatcher> { TestCoroutineDispatcher() }
        },
        viewModelModule,
        overridesModule
    )

    return org.koin.core.context.startKoin {
        modules(allModules)
        logger(EmptyLogger())
    }
}
