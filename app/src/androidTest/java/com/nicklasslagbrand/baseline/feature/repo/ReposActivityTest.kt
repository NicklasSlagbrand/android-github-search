package com.nicklasslagbrand.baseline.feature.repo

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.testRepo
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ReposActivityTest {

    @Test
    fun testNavigationToDetailsScreen() {
        launchFragmentInContainer<RepoDetailsFragment>(
            fragmentArgs = bundleOf(
                "repo" to testRepo
            )
        )
        onView(withId(R.id.ivAvatar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
    }

}
