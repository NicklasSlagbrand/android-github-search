package com.valtech.baseline

import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.domain.model.Owner

@SuppressWarnings("MagicNumber")
val testRepo = GithubRepo(
    id = 1,
    title = "repoTitle",
    description = "cool repo",
    owner = Owner(
        avatarUrl = ""
    )
)
