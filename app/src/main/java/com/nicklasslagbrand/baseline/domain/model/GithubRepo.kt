package com.nicklasslagbrand.baseline.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("full_name")
    val title: String,
    @SerializedName("description")
    val description: String? = "",
    val owner: Owner
): Parcelable

@Parcelize
data class Owner(@SerializedName("avatar_url") val avatarUrl: String? = ""): Parcelable
