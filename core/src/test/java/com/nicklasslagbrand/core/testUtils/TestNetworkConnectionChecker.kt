package com.nicklasslagbrand.core.testUtils

import com.nicklasslagbrand.core.network.NetworkConnectionChecker

class TestNetworkConnectionChecker(var isConnected: Boolean = false) :
    NetworkConnectionChecker {
    override val isNotConnected: Boolean
        get() = !isConnected
}
