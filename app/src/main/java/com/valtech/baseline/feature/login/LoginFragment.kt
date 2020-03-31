package com.valtech.baseline.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.disable
import com.valtech.baseline.core.extension.enable
import com.valtech.baseline.core.extension.observeEvents
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.error.Error.MissingNetworkConnection
import com.valtech.baseline.feature.base.BaseFragment
import com.valtech.baseline.feature.login.LoginViewModel.Event
import com.valtech.baseline.feature.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.btnLogin
import kotlinx.android.synthetic.main.fragment_login.btnSignUp
import kotlinx.android.synthetic.main.fragment_login.etEmail
import kotlinx.android.synthetic.main.fragment_login.etPassword
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.loginLiveData) {
            handleEvents(it)
        }
        observeEvents(viewModel.failure) {
            handleLoginError(it)
        }
    }

    private fun handleLoginError(error: Error) {
        btnLogin.enable()

        when (error) {
            MissingNetworkConnection -> notifyError(R.string.error_network_connection)
            else -> notifyError(R.string.error_general_error)
        }
    }

    private fun handleEvents(event: Event) {
        when (event) {
            Event.LoginSuccessfullyCompleted -> navigateToMainScreen()
            Event.EmailFieldIsEmpty -> {
                btnLogin.enable()
                hideProgress()
                etEmail.error = str(R.string.error_email_or_password_is_not_valid)
            }
            Event.PasswordFieldIsEmpty -> {
                btnLogin.enable()
                hideProgress()
                etPassword.error = str(R.string.error_email_or_password_is_not_valid)
            }
        }
    }

    private fun navigateToMainScreen() {
        MainActivity.startActivity(requireContext())

        activity?.finish()
    }

    private fun initView() {
        btnLogin.attachTextChangeAnimator()

        btnLogin.setOnClickListener {
            btnLogin.disable()
            showProgress()
            viewModel.onLoginClicked(etEmail.text.toString(), etPassword.text.toString())
        }

        btnSignUp.setOnClickListener {
            notifyWarning(R.string.error_not_implemented_yet)
        }
    }

    private fun showProgress() {
        btnLogin.showProgress()
    }
    private fun hideProgress() {
        btnLogin.hideProgress()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
