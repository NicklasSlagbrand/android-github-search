package com.nicklasslagbrand.baseline.data.datasource.local

import com.nicklasslagbrand.baseline.data.entity.converter.RealmGithubRepoConverter
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import io.realm.Realm

class LocalGithubRepoRepository {
    fun storeRepoList(githubRepos: List<GithubRepo>): Any {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                githubRepos.forEach { repo ->
                    val realmAttraction = RealmGithubRepoConverter.fromRepo(repo)
                    realm.insertOrUpdate(realmAttraction)
                }
            }
        }
        return Any()
    }
}
