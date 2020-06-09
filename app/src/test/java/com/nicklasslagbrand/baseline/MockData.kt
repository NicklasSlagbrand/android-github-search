package com.nicklasslagbrand.baseline

import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.model.Owner

@SuppressWarnings("MagicNumber")
val testRepo = GithubRepo(
    id = 1,
    title = "repoTitle",
    description = "cool repo",
    owner = Owner(
        avatarUrl = ""
    )
)
