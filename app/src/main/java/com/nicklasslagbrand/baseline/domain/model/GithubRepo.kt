package com.nicklasslagbrand.baseline.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RepoSearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<GithubRepo> = emptyList()
)
@Parcelize
data class GithubRepo(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String,
    @field:SerializedName("stargazers_count") val stars: Int,
    @field:SerializedName("forks_count") val forks: Int,
    @field:SerializedName("language") val language: String?,
    @field:SerializedName("owner") val owner: Owner
) : Parcelable


@Parcelize
data class Owner (
    @field:SerializedName("avatar_url") val avatarUrl: String
) : Parcelable