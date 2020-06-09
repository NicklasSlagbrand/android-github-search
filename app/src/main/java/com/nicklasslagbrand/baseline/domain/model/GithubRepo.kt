package com.nicklasslagbrand.baseline.domain.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("full_name")
    val title: String,
    @SerializedName("description")
    val description: String? = "",
    val owner: Owner
)
data class Owner(@SerializedName("avatar_url") val avatarUrl: String? = "")
