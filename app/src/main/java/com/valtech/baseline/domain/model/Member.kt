package com.valtech.baseline.domain.model

import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("title") val title: String,
    @SerializedName("locationString") val locationString: String,
    @SerializedName("avatarUrl") val avatarUrl: String,
    @SerializedName("location") val location: Location,
    @SerializedName("summary") val summary: String,
    @SerializedName("linkedInUrl") val linkedInUrl: String,
    @SerializedName("skills") val skills: List<String>
) {
    val fullName: String
        get() = "$firstName $lastName"
}

data class Location(
    @SerializedName("latitude") val latitude: Float,
    @SerializedName("longitude") val longitude: Float
)
