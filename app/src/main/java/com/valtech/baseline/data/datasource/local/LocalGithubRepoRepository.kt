package com.valtech.baseline.data.datasource.local

import com.valtech.baseline.data.entity.converter.RealmGithubRepoConverter
import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.data.entity.RealmMember
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class LocalGithubRepoRepository {
    fun storeMembers(githubRepos: List<GithubRepo>): Any {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                githubRepos.forEach { member ->
                    val realmAttraction = RealmGithubRepoConverter.fromMember(member)
                    realm.insertOrUpdate(realmAttraction)
                }
            }
        }
        return Any()
    }

    fun getAllMembers(): List<GithubRepo> {
        Realm.getDefaultInstance().use { realm ->
            val realmResults = realm.where<RealmMember>().findAll()
            return handleQueryResults(realmResults, realm)
        }
    }

    private fun handleQueryResults(realmResults: RealmResults<RealmMember>, realm: Realm): List<GithubRepo> {
        val realmOrders = realm.copyFromRealm(realmResults)
        return realmOrders.map {
            RealmGithubRepoConverter.toMember(it)
        }
    }
}
