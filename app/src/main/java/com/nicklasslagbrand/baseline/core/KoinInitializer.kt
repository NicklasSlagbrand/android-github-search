package com.nicklasslagbrand.baseline.core

import android.content.Context
import androidx.startup.Initializer
import com.nicklasslagbrand.baseline.core.di.androidPlatformModule
import com.nicklasslagbrand.baseline.core.di.networkModule
import com.nicklasslagbrand.baseline.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinInitializer: Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidLogger(Level.ERROR)
            androidContext(context)

            modules(
                listOf(
                    androidPlatformModule,
                    networkModule("https://api.github.com/"),
                    viewModelModule
                )
            )
        }
    }
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}