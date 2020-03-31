package com.valtech.baseline.domain

interface TimeHandler {
    fun isBeforeNow(date: String): Boolean
}
