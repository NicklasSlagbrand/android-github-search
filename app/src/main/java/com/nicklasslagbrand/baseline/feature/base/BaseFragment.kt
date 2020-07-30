package com.nicklasslagbrand.baseline.feature.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.awesome.shorty.AwesomeToast
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.domain.error.Error

@SuppressLint("Registered")
open class BaseFragment : Fragment() {
    protected open fun provideLayoutId(): Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return provideLayoutId()?.let {
            inflater.inflate(it, container, false)
        } ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    fun handleFailure(errorEvent: Error) {
        if (errorEvent is Error.MissingNetworkConnection) {
            notifyError(getString(R.string.network_error))
        }
    }

    private fun notifyError(message: String) =
            AwesomeToast.error(
                activity as Context, message,
                DEFAULT_TOAST_DURATION_SEC
            ).show()

    companion object {
        const val DEFAULT_TOAST_DURATION_SEC = 5
    }
}
