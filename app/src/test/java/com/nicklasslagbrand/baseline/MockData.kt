package com.nicklasslagbrand.baseline

import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.model.RepoSearchResponse

@SuppressWarnings("MagicNumber")
val testRepo = GithubRepo(
    id = 82128465,
    name = "Android",
    fullName = "open-android/Android",
    description = "This is a description",
    url = "https://api.github.com/repos/open-android/Android",
    stars = 10969,
    forks = 3049,
    language = null
)

@SuppressWarnings("MagicNumber")
val testSearchResponse = RepoSearchResponse(
    total = 1122077,
    items = listOf(testRepo)
)