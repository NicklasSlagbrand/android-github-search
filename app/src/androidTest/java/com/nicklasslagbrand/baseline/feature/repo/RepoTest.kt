package com.nicklasslagbrand.baseline.feature.repo

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nicklasslagbrand.baseline.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class RepoTest {

    @Test
    fun validateDetailScreen() {
        initializeDetailView()
        details {
            matchDescriptionText(testRepo.description!!)
            matchTitleText(testRepo.title)
        }
    }

    private fun initializeDetailView(){
        launchFragmentInContainer<RepoDetailFragment>(bundleOf("repo" to testRepo))
    }
}