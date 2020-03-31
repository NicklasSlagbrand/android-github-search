package com.valtech.baseline.feature.base

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.valtech.baseline.domain.error.Error
import timber.log.Timber

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    fun handleFailure(errorEvent: Error) {
        if (errorEvent is Error.GeneralError) {
            Timber.e("Faced an error: ${errorEvent.exception}")
            errorEvent.exception.printStackTrace()
        }
        showToast("Faced an error: $errorEvent.")
    }

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
