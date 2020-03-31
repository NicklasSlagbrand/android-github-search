package com.valtech.baseline.feature.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.awesome.shorty.AwesomeToast
import com.valtech.baseline.domain.error.Error
import timber.log.Timber

@SuppressLint("Registered")
open class BaseFragment : Fragment() {
    protected open fun provideLayoutId(): Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return provideLayoutId()?.let {
            inflater.inflate(it, container, false)
        } ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    fun handleFailure(errorEvent: Error) {
        if (errorEvent is Error.GeneralError) {
            Timber.e("Faced an error: ${errorEvent.exception}")
            errorEvent.exception.printStackTrace()
        }
        notifyError("Faced an error: $errorEvent.")
    }

    fun str(res: Int) = getString(res)

    internal fun notifyWarning(@StringRes message: Int) =
        AwesomeToast.warning(
            activity as Context, str(message),
            DEFAULT_TOAST_DURATION_SEC
        ).show()

    internal fun notifyWarning(message: String) =
        AwesomeToast.warning(
            activity as Context, message,
            DEFAULT_TOAST_DURATION_SEC
        ).show()

    internal fun notifyError(@StringRes message: Int) =
        AwesomeToast.error(
            activity as Context, str(message),
            DEFAULT_TOAST_DURATION_SEC
        ).show()

    internal fun notifyError(message: String) =
        AwesomeToast.error(
            activity as Context, message,
            DEFAULT_TOAST_DURATION_SEC
        ).show()

    companion object {
        const val DEFAULT_TOAST_DURATION_SEC = 5
    }
}
