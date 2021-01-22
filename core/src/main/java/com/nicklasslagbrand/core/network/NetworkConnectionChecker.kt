package com.nicklasslagbrand.core.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

interface NetworkConnectionChecker {
    val isNotConnected: Boolean

    class AndroidNetworkConnectionChecker(private val context: Context) :
        NetworkConnectionChecker {
        override val isNotConnected: Boolean
            get() = !isNetworkOnline()

        private fun isOnline(): Boolean {
            var isNetworkConnected = false
            (context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
                for (network in allNetworks) {
                    val networkCapabilities = getNetworkCapabilities(network)
                    if (networkCapabilities != null) {
                        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                        ) isNetworkConnected = true
                    }
                }
            }
            return isNetworkConnected
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun checkNetworkConnectionMinApi23(): Boolean {
            (context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
                return getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                } ?: false
            }
        }

        private fun isNetworkOnline(): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkNetworkConnectionMinApi23()
            } else isOnline()
        }
    }

}
