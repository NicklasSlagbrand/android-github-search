package com.nicklasslagbrand.baseline.testutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainSame

class TestObserver<T> : Observer<T> {
    private val observedValues = mutableListOf<T>()

    fun skipPreviousLiveDataEvents() {
        observedValues.clear()
    }

    fun printValues() {
        println("Total count = ${observedValues.size}")
        observedValues.forEach {
            println("item = $it")
        }
    }

    fun <Event> shouldContainEvents(vararg events: Event) {
        val wrapped = events.map { ConsumableEvent(it) }
        observedValues.shouldContainSame(wrapped)
    }

    fun <T> shouldContainValues(vararg values: T) {
        observedValues.shouldContainSame(values.asList())
    }

    fun shouldBeEmpty() {
        observedValues.size.shouldBeEqualTo(0)
    }

    override fun onChanged(value: T) {
        observedValues.add(value)
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
