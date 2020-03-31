package com.valtech.baseline.feature.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.valtech.baseline.data.viewmodel.ConsumableEvent
import com.valtech.baseline.domain.model.Member
import com.valtech.baseline.domain.usecase.GetTeamMembersUseCase
import com.valtech.baseline.domain.usecase.StoreTeamMembersUseCase
import com.valtech.baseline.domain.usecase.UseCase
import com.valtech.baseline.feature.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamMembersViewModel(
    private val getTeamMembersUseCase: GetTeamMembersUseCase,
    private val storeTeamMembersUseCase: StoreTeamMembersUseCase,
    private val backgroundDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun initialize() {
        viewModelScope.launch {
            val result = withContext(backgroundDispatcher) {
                getTeamMembersUseCase.call(UseCase.None)
            }
            result.fold({
                eventsLiveData.value = ConsumableEvent(Event.ShowTeam(it))
            }, ::handleFailure)
        }
    }

    private fun storeMembers(list: List<Member>) {
        viewModelScope.launch {
            val result = withContext(backgroundDispatcher) {
                storeTeamMembersUseCase.call(list)
            }
            result.fold({
            }, ::handleFailure)
        }
    }

    sealed class Event {
        data class ShowTeam(val members: List<Member>) : Event()
    }
}
