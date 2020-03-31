package com.valtech.baseline.core.extension

import androidx.lifecycle.Observer
import com.valtech.baseline.data.viewmodel.ConsumableEvent

class EventObserver<T>(private val onEventUnconsumedContent: (T) -> Unit) : Observer<ConsumableEvent<T>> {
    override fun onChanged(event: ConsumableEvent<T>) {
        event.consume(onEventUnconsumedContent)
    }
}
