package com.nicklasslagbrand.baseline.testutils

import com.nicklasslagbrand.baseline.core.di.generalAppModule
import com.nicklasslagbrand.baseline.core.di.useCaseAndViewModelModule
import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker
import com.nicklasslagbrand.baseline.domain.TimeHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.core.KoinApplication
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("EXPERIMENTAL_API_USAGE")
fun startKoin(
    baseUrl: String = "",
    networkLogging: Boolean = false,
    overridesModule: Module
): KoinApplication {
    // Gather all required dependencies
    val allModules = listOf(
        generalAppModule(baseUrl, networkLogging),
        module {
            single<TimeHandler> { NoopTimeHandler() }
            single<NetworkConnectionChecker> { TestNetworkConnectionChecker(true) }
            single<CoroutineDispatcher> { TestCoroutineDispatcher() }
        },
        useCaseAndViewModelModule(),
        overridesModule
    )

    return org.koin.core.context.startKoin {
        modules(allModules)
        logger(EmptyLogger())
    }
}
