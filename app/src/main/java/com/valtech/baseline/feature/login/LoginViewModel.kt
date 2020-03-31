package com.valtech.baseline.feature.login

import androidx.lifecycle.MutableLiveData
import com.valtech.baseline.data.viewmodel.ConsumableEvent
import com.valtech.baseline.feature.base.BaseViewModel
import com.valtech.baseline.feature.login.LoginViewModel.Event.EmailFieldIsEmpty
import com.valtech.baseline.feature.login.LoginViewModel.Event.PasswordFieldIsEmpty

class LoginViewModel : BaseViewModel() {
    val loginLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun onLoginClicked(email: String, password: String) {

        when {
            email.isBlank() -> loginLiveData.value =
                ConsumableEvent(EmailFieldIsEmpty)
            password.isBlank() -> {
                ConsumableEvent(PasswordFieldIsEmpty)
            }
            else -> handleAuthResponse()
        }

//        addDisposable {
//            loginUseCase.call(AuthParam(email, password)).subscribe { result ->
//                result.fold({
//                    handleAuthResponse(it)
//                }, ::handleFailure)
//            }
//        }
    }

    private fun handleAuthResponse() {
        loginLiveData.value = ConsumableEvent(Event.LoginSuccessfullyCompleted)
    }

    sealed class Event {
        object LoginSuccessfullyCompleted : Event()
        object EmailFieldIsEmpty : Event()
        object PasswordFieldIsEmpty : Event()
    }
}
