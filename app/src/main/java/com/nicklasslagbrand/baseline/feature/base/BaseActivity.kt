package com.nicklasslagbrand.baseline.feature.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.nicklasslagbrand.baseline.domain.error.Error
import timber.log.Timber

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    @LayoutRes
    protected open fun provideLayoutId(): Int? = null
    protected open fun applyExternalArguments(args: Bundle) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideLayoutId()?.let { layoutId ->
            setContentView(layoutId)
        }
        intent.extras?.let { applyExternalArguments(it) }
    }

    fun handleFailure(errorEvent: Error) {
        if (errorEvent is Error.GeneralError) {
            Timber.e("Faced an error: ${errorEvent.exception}")
            errorEvent.exception.printStackTrace()
        }
        showToast("Faced an error: $errorEvent.")
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
