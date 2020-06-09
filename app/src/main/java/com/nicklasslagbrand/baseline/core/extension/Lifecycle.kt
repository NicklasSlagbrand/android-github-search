package com.nicklasslagbrand.baseline.core.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, NonNullObserver(body))

fun <T : Any, L : LiveData<ConsumableEvent<T>>> LifecycleOwner.observeEvents(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, EventObserver {
        body(it)
    })

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, NonNullObserver(body))

fun <T : Any, L : LiveData<ConsumableEvent<T>>> Fragment.observeEvents(liveData: L, body: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, EventObserver {
        body(it)
    })

private class NonNullObserver<T : Any>(val body: (T) -> Unit) : Observer<T> {
    override fun onChanged(data: T?) {
        requireNotNull(data)

        body(data)
    }
}
