package com.valtech.baseline.data.datasource.local

import android.content.SharedPreferences

open class PreferenceStorage(private val prefs: SharedPreferences) {

    open var someSetting: String?
        get() {
            return prefs.getString(someSetting, null)
        }
        set(value) {
            prefs.edit().apply {
                putString(someSetting, value)

                apply()
            }
        }

    fun clearApiTokensAndCredentials() {
        prefs.edit().apply {
            remove(SOME_SETTING_KEY)

        }.apply()
    }

    companion object {
        private const val SOME_SETTING_KEY = "key_some_setting"
    }
}
