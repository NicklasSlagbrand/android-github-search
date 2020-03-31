package com.valtech.baseline.data.time

import com.valtech.baseline.domain.TimeHandler
import org.joda.time.DateTime

class AndroidTimeHandler : TimeHandler {
    override fun isBeforeNow(date: String) = DateTime.parse(date).isBeforeNow
}
