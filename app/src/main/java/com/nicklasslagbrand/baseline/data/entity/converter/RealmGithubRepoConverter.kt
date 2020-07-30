package com.nicklasslagbrand.baseline.data.entity.converter

import com.nicklasslagbrand.baseline.data.entity.RealmOwner
import com.nicklasslagbrand.baseline.data.entity.RealmRepo
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.model.Owner

object RealmGithubRepoConverter {
    fun toRepo(realmRepo: RealmRepo): GithubRepo {
        with(realmRepo) {
            return GithubRepo(id, title, description, Owner(owner?.avatarUrl))
        }
    }

    fun fromRepo(githubRepo: GithubRepo): RealmRepo {
        with(githubRepo) {
            return RealmRepo(id, title, description ?: "",
                RealmOwner(owner.avatarUrl ?: ""))
        }
    }
}
