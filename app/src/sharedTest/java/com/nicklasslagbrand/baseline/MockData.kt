package com.nicklasslagbrand.baseline

import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.model.Owner

@SuppressWarnings("MagicNumber")
val testRepo = GithubRepo(
    id = 10699052,
    title = "android/android.github.io",
    description = "description",
    owner = Owner(
        avatarUrl = "https://avatars3.githubusercontent.com/u/32689599?v=4"
    )
)
