package com.nicklasslagbrand.baseline.testutils

import com.nicklasslagbrand.baseline.domain.TimeHandler

class NoopTimeHandler : TimeHandler {
    override fun isBeforeNow(date: String) = false
}
