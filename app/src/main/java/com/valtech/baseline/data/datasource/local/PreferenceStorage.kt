package com.valtech.baseline.data.datasource.local

import android.content.SharedPreferences

open class PreferenceStorage(private val prefs: SharedPreferences) {

    open var apiToken: String?
        get() {
            return prefs.getString(API_TOKENS_KEY, null)
        }
        set(value) {
            prefs.edit().apply {
                putString(API_TOKENS_KEY, value)

                apply()
            }
        }

    var apiEmail: String?
        get() {
            return prefs.getString(API_EMAIL_KEY, null)
        }
        set(value) {
            prefs.edit().apply {
                putString(API_EMAIL_KEY, value)

                apply()
            }
        }

    var apiPassword: String?
        get() {
            return prefs.getString(API_PASSWORD_KEY, null)
        }
        set(value) {
            prefs.edit().apply {
                putString(API_PASSWORD_KEY, value)

                apply()
            }
        }

    fun clearApiTokensAndCredentials() {
        prefs.edit().apply {
            remove(API_TOKENS_KEY)
            remove(API_EMAIL_KEY)
            remove(API_PASSWORD_KEY)
        }.apply()
    }

    companion object {
        private const val API_TOKENS_KEY = "api_tokens"
        private const val API_EMAIL_KEY = "api_email"
        private const val API_PASSWORD_KEY = "api_password"
    }

    class TestPreferenceStorage(
        prefs: SharedPreferences,
        override var apiToken: String?
    ) : PreferenceStorage(prefs)
}
