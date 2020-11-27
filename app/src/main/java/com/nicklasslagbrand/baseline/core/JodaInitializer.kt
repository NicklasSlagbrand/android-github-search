package com.nicklasslagbrand.baseline.core

import android.content.Context
import androidx.startup.Initializer
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class JodaInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        JodaTimeAndroid.init(context)
        Timber.d("JodaInitializer is initialized.")

    }
    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(TimberInitializer::class.java)
    }
}