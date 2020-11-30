package com.nicklasslagbrand.baseline.testutils

import com.nicklasslagbrand.baseline.data.network.NetworkConnectionChecker

class TestNetworkConnectionChecker(var isConnected: Boolean = false) :
    NetworkConnectionChecker {
    override val isNotConnected: Boolean
        get() = !isConnected
}
