package com.nicklasslagbrand.baseline.feature.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import com.nicklasslagbrand.baseline.domain.error.Error

open class BaseViewModel : ViewModel() {
    val failure = MutableLiveData<ConsumableEvent<Error>>()

    fun handleFailure(error: Error) {
        this.failure.value = ConsumableEvent(error)
    }
}
