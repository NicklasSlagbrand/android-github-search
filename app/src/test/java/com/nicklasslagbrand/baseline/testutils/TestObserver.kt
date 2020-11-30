package com.nicklasslagbrand.baseline.testutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import org.amshove.kluent.shouldContainSame

class TestObserver<T> : Observer<T> {
    private val observedValues = mutableListOf<T>()

    fun <Event> shouldContainEvents(vararg events: Event) {
        val wrapped = events.map { ConsumableEvent(it) }
        observedValues.shouldContainSame(wrapped)
    }

    override fun onChanged(value: T) {
        observedValues.add(value)
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
