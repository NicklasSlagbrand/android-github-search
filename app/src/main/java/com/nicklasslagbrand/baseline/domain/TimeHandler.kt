package com.nicklasslagbrand.baseline.domain

interface TimeHandler {
    fun isBeforeNow(date: String): Boolean
}
