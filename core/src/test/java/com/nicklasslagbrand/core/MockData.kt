package com.nicklasslagbrand.core

import com.nicklasslagbrand.core.entity.GithubRepo
import com.nicklasslagbrand.core.entity.Owner
import com.nicklasslagbrand.core.entity.RepoSearchResponse

@SuppressWarnings("MagicNumber")
val testOwner = Owner("")

@SuppressWarnings("MagicNumber")
val testRepo = GithubRepo(
    id = 82128465,
    name = "Android",
    fullName = "open-android/Android",
    description = "This is a description",
    url = "https://api.github.com/repos/open-android/Android",
    stars = 10969,
    forks = 3049,
    language = null,
    owner = testOwner
)

@SuppressWarnings("MagicNumber")
val testSearchResponse = RepoSearchResponse(
    total = 1122077,
    items = listOf(testRepo)
)