package com.valtech.baseline.data.entity.converter

import com.valtech.baseline.domain.model.Location
import com.valtech.baseline.domain.model.Member
import com.vartech.baseline.data.entity.RealmLocation
import com.vartech.baseline.data.entity.RealmMember
import io.realm.RealmList

object RealmMemberConverter {
    fun toMember(realmMember: RealmMember): Member {
        with(realmMember) {

            val firstName = firstName
            val lastName = lastName
            val title = title
            val locationString = locationString
            val avatarUrl = avatarUrl
            val summary = summary
            val linkedInUrl = linkedInUrl

            val location = Location(location!!.latitude, location!!.longitude)

            val skills = skills?.map {
                it
            } ?: emptyList()

            return Member(
                firstName, lastName, title, locationString, avatarUrl,
                location, summary, linkedInUrl, skills)
        }
    }

    fun fromMember(member: Member): RealmMember {
        with(member) {

            val firstName = firstName
            val lastName = lastName
            val title = title
            val locationString = locationString
            val avatarUrl = avatarUrl
            val summary = summary
            val linkedInUrl = linkedInUrl

            val location = RealmLocation(location.latitude, location.longitude)

            val skills = RealmList<String>()

            skills.forEach {
                skills.add(it)
            }

            return RealmMember(firstName, lastName, title, locationString, avatarUrl,
                location, summary, linkedInUrl, skills)
        }
    }
}
