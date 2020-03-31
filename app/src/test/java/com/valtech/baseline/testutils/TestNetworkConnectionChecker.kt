package com.valtech.baseline.testutils

import com.valtech.baseline.data.network.NetworkConnectionChecker

class TestNetworkConnectionChecker(var isConnected: Boolean = false) :
    NetworkConnectionChecker {
    override val isNotConnected: Boolean
        get() = !isConnected
}
