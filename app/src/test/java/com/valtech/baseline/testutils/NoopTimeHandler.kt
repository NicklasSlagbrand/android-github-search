package com.valtech.baseline.testutils

import com.valtech.baseline.domain.TimeHandler

class NoopTimeHandler : TimeHandler {
    override fun isBeforeNow(date: String) = false
}
