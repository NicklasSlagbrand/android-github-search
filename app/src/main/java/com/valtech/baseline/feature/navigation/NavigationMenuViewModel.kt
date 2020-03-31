package com.valtech.baseline.feature.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valtech.baseline.data.viewmodel.ConsumableEvent
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel.Event.ShowBackView
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel.Event.ShowTeamView

class NavigationMenuViewModel : ViewModel() {
    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun initialize() {
        showTeamView()
    }

    fun showTeamView() {
        eventsLiveData.value = ConsumableEvent(ShowTeamView)
    }

    fun showNetworkView() {
        eventsLiveData.value = ConsumableEvent(Event.ShowNetworkView)
    }

    fun showExchangeView() {
        eventsLiveData.value = ConsumableEvent(Event.ShowExchangeView)
    }

    fun showMessageView() {
        eventsLiveData.value = ConsumableEvent(Event.ShowMessageView)
    }

    fun onBackToScreen(shouldGoToCards: Boolean) {
        if (shouldGoToCards) {
            eventsLiveData.value = ConsumableEvent(ShowBackView)
        }
    }

    sealed class Event {
        object ShowTeamView : Event()
        object ShowExchangeView : Event()
        object ShowNetworkView : Event()
        object ShowMessageView : Event()
        object ShowBackView : Event()
    }
}
