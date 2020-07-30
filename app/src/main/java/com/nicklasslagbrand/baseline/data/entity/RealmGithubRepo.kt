package com.nicklasslagbrand.baseline.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmRepo(
    @PrimaryKey
    var id: Int? = 0,
    var title: String = "",
    var description: String = "",
    var owner: RealmOwner? = null
) : RealmObject()

open class RealmOwner(
    var avatarUrl: String = ""
) : RealmObject()
