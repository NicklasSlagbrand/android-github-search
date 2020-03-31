package com.valtech.baseline

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.valtech.baseline.feature.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest : AutoCloseKoinTest() {

    private var userName = "mail@mail.com"
    private var password = "password"

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> =
        ActivityTestRule(LoginActivity::class.java)

    @Test
    @SuppressWarnings("MagicNumber")
    fun signinTest() {
        Thread.sleep(1500)

        onView(withId(R.id.etEmail))
            .perform(typeText(userName))
        onView(withId(R.id.etPassword))
            .perform(typeText(password))
        onView(withId(R.id.btnLogin))
            .perform(click())
    }
}
