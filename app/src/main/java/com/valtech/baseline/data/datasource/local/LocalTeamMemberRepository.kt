package com.valtech.baseline.data.datasource.local

import com.valtech.baseline.data.entity.converter.RealmMemberConverter
import com.valtech.baseline.domain.model.Member
import com.vartech.baseline.data.entity.RealmMember
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class LocalTeamMemberRepository {
    fun storeMembers(members: List<Member>): Any {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                members.forEach { member ->
                    val realmAttraction = RealmMemberConverter.fromMember(member)
                    realm.insertOrUpdate(realmAttraction)
                }
            }
        }
        return Any()
    }

    fun getAllMembers(): List<Member> {
        Realm.getDefaultInstance().use { realm ->
            val realmResults = realm.where<RealmMember>().findAll()
            return handleQueryResults(realmResults, realm)
        }
    }

    private fun handleQueryResults(realmResults: RealmResults<RealmMember>, realm: Realm): List<Member> {
        val realmOrders = realm.copyFromRealm(realmResults)
        return realmOrders.map {
            RealmMemberConverter.toMember(it)
        }
    }
}
