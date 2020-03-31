package com.valtech.baseline.data.entity.converter

import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.data.entity.RealmMember
import com.valtech.baseline.data.entity.RealmOwner
import com.valtech.baseline.domain.model.Owner

object RealmGithubRepoConverter {
    fun toMember(realmMember: RealmMember): GithubRepo {
        with(realmMember) {
            return GithubRepo(id, title, description, Owner(owner?.avatarUrl))
        }
    }

    fun fromMember(githubRepo: GithubRepo): RealmMember {
        with(githubRepo) {
            return RealmMember(id, title, description?:  "", RealmOwner(owner.avatarUrl?:""))
        }
    }
}
