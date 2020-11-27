package com.nicklasslagbrand.baseline.core

import android.content.Context
import androidx.startup.Initializer
import com.nicklasslagbrand.baseline.BuildConfig
import com.nicklasslagbrand.baseline.core.di.androidPlatformModule
import com.nicklasslagbrand.baseline.core.di.generalAppModule
import com.nicklasslagbrand.baseline.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class TimberInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("TimberInitializer is initialized.")

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}