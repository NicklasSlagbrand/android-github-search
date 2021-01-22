package com.nicklasslagbrand.core.error

sealed class Error {
    data class GeneralError(val exception: Throwable) : Error() {
        override fun toString(): String {
            return "GeneralError(exception=$exception)"
        }
    }

    object MissingNetworkConnection : Error()
}
