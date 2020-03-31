package com.valtech.baseline.feature.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valtech.baseline.data.viewmodel.ConsumableEvent
import com.valtech.baseline.domain.error.Error

open class BaseViewModel : ViewModel() {
    val failure = MutableLiveData<ConsumableEvent<Error>>()

    fun handleFailure(error: Error) {
        this.failure.value = ConsumableEvent(error)
    }
}
