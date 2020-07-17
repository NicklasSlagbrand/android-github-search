package com.nicklasslagbrand.baseline

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.nicklasslagbrand.baseline.core.di.androidPlatformModule
import com.nicklasslagbrand.baseline.core.di.generalAppModule
import com.nicklasslagbrand.baseline.core.di.useCaseAndViewModelModule
import io.realm.Realm
import io.realm.RealmConfiguration
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@Suppress("unused")
class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        JodaTimeAndroid.init(this)

        setupRealm()

        setupKoin()

        createAppNotificationChannel()
    }

    private fun setupRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .name("baseline.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        )
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.ERROR)
            androidContext(this@AndroidApp)

            modules(
                listOf(
                    androidPlatformModule(),
                    generalAppModule(
                        baseUrl = BuildConfig.API_BASE_URL,
                        networkLogging = true
                    ),
                    useCaseAndViewModelModule()
                )
            )
        }
    }

    private fun createAppNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    Constants.DEFAULT_NOTIFICATION_CHANNEL,
                    getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_HIGH
                )

            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }
}
