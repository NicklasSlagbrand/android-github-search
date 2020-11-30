package com.nicklasslagbrand.baseline.feature.repo.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.feature.repo.RepoDetailsFragment
import com.nicklasslagbrand.baseline.testRepo

import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ReposDetailFragmentTest {
    @Test
    fun testNavigationToDetailsScreen() {
        launchFragmentInContainer<RepoDetailsFragment>(bundleOf("repo" to testRepo))
        onView(withId(R.id.ivAvatar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(testRepo.description)))
        onView(withId(R.id.tvTitle)).check(matches(withText(testRepo.title)))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
    }

}
