package com.vartech.baseline.data.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmMember(
    @PrimaryKey
    var firstName: String = "",
    var lastName: String = "",
    var title: String = "",
    var locationString: String = "",
    var avatarUrl: String = "",
    var location: RealmLocation? = null,
    var summary: String = "",
    var linkedInUrl: String = "",
    var skills: RealmList<String>? = null
) : RealmObject() {
    val fullName: String
        get() = "$firstName $lastName"
}

open class RealmLocation(
    var latitude: Float = 0F,
    var longitude: Float = 0F
) : RealmObject()
