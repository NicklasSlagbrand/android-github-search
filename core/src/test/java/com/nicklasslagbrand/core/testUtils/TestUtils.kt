@file:Suppress("unused")
package com.nicklasslagbrand.core.testUtils

import org.junit.Assert.fail
import com.nicklasslagbrand.core.error.Error


fun failIfError(error: Error) {
    fail("Something went wrong. Error callback should not be triggered here. Error : $error")
}

fun failIfException(e: Exception) {
    fail("Something went wrong. Exception callback should not be triggered here. Error : $e")
}

fun failIfSuccess(value: Any) {
    fail("Something went wrong. Successful callback should not be triggered here. Value : $value")
}

fun doNothingForSuccess(value: Any) {
    // Everything is OK. Just skipp.
}
fun doNothingForFail(value: Any) {
    // Everything is OK. Just skipp.
}
