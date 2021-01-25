package com.nicklasslagbrand.core

import com.nicklasslagbrand.core.entity.GithubRepo
import com.nicklasslagbrand.core.entity.Owner
import com.nicklasslagbrand.core.entity.RepoSearchResponse

@SuppressWarnings("MagicNumber")
val testOwner = Owner("https://avatars.githubusercontent.com/u/23095877?v=4")

@SuppressWarnings("MagicNumber")
val testRepo = GithubRepo(
    id = 123,
    name = "Android",
    fullName = "open-android/Android",
    description = "This is a description",
    stars = 2,
    forks = 10,
    language = null,
    owner = testOwner
)

@SuppressWarnings("MagicNumber")
val testSearchResponse = RepoSearchResponse(
    total = 1,
    items = listOf(testRepo)
)