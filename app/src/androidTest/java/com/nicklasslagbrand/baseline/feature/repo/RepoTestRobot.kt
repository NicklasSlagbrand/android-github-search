package com.nicklasslagbrand.baseline.feature.repo
import com.nicklasslagbrand.baseline.BaseTestRobot
import com.nicklasslagbrand.baseline.R

fun details(func: RepoDetailsTestRobot.() -> Unit) = RepoDetailsTestRobot()
        .apply { func() }

class RepoDetailsTestRobot : BaseTestRobot(){
    fun matchDescriptionText(description: String) = matchText(R.id.tvDescription, description)
    fun matchTitleText(title: String) = matchText(R.id.tvTitle, title)
}