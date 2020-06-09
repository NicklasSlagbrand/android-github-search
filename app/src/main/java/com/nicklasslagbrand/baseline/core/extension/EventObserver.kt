package com.nicklasslagbrand.baseline.core.extension

import androidx.lifecycle.Observer
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent

class EventObserver<T>(private val onEventUnconsumedContent: (T) -> Unit) : Observer<ConsumableEvent<T>> {
    override fun onChanged(event: ConsumableEvent<T>) {
        event.consume(onEventUnconsumedContent)
    }
}
