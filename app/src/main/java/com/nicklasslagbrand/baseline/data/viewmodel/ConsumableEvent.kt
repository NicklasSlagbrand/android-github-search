package com.nicklasslagbrand.baseline.data.viewmodel

import androidx.lifecycle.LiveData

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * This should be used for listening single events in [LiveData].
 */
data class ConsumableEvent<out T>(val content: T) {
    private var consumed = false

    fun consume(consumer: (T) -> Unit) {
        if (not(consumed)) {
            consumer(content)
        }
        consumed = true
    }

    fun not(condition: Boolean) = !condition
}
