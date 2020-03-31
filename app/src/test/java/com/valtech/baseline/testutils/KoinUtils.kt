package com.valtech.baseline.testutils

import com.valtech.baseline.Constants
import com.valtech.baseline.data.di.generalAppModule
import com.valtech.baseline.data.di.useCaseAndViewModelModule
import com.valtech.baseline.data.network.NetworkConnectionChecker
import com.valtech.baseline.domain.TimeHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.core.KoinApplication
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

@Suppress("EXPERIMENTAL_API_USAGE")
fun startKoin(
    baseUrl: String = "http://private-fd22c-valtechmobilebaselineapi.apiary-mock.com",
    networkLogging: Boolean = false,
    overridesModule: Module
): KoinApplication {
    // Gather all required dependencies
    val allModules = listOf(
        generalAppModule(baseUrl, networkLogging),
        module {
            single(named(Constants.DEVICE_ID_TAG)) { "TEST_DEVICE_ID" }
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
