package com.nicklasslagbrand.baseline.testutils

import com.nicklasslagbrand.baseline.domain.error.Error
import org.junit.Assert.fail

fun failIfError(error: Error) {
    fail("Something went wrong. Error callback should not be triggered here. Error : $error")
}

fun failIfSuccess(value: Any) {
    fail("Something went wrong. Successful callback should not be triggered here. Value : $value")
}

fun doNothingForSuccess(value: Any) {
    // Everything is OK. Just skipp.
}
