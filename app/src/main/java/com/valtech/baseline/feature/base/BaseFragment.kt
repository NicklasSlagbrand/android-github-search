package com.valtech.baseline.feature.base

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.awesome.shorty.AwesomeToast
import com.valtech.baseline.domain.error.Error
import timber.log.Timber

@SuppressLint("Registered")
open class BaseFragment : Fragment() {

    fun handleFailure(errorEvent: Error) {
        if (errorEvent is Error.GeneralError) {
            Timber.e("Faced an error: ${errorEvent.exception}")
            errorEvent.exception.printStackTrace()
        }
        notifyError("Faced an error: $errorEvent.")
    }

    fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    fun str(res: Int) = getString(res)

    internal fun notifySuccess(@StringRes message: Int) =
        AwesomeToast.success(
            activity as Context, str(message),
            DEFAULT_TOAST_DURATION_SEC
        ).show()

    internal fun notifySuccess(message: String) =
        AwesomeToast.success(
            activity as Context, message,
            DEFAULT_TOAST_DURATION_SEC
        ).show()

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
