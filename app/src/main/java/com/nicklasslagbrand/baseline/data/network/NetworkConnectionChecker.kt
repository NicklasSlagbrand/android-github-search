package com.nicklasslagbrand.baseline.data.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

interface NetworkConnectionChecker {
    val isNotConnected: Boolean

    class AndroidNetworkConnectionChecker(private val context: Context) :
        NetworkConnectionChecker {
        override val isNotConnected: Boolean
            get() = !isConnected()

        private fun isConnected(): Boolean {
            val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo?.isConnected ?: false
        }
    }
}
