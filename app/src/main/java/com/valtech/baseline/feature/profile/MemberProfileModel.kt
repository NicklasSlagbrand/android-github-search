package com.valtech.baseline.feature.profile

import android.os.Parcelable
import com.valtech.baseline.domain.model.Member
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemberProfileModel(
    val fullName: String,
    val locationString: String,
    val avatarUrl: String,
    val summary: String,
    val skills: List<String>
) : Parcelable {
    companion object {
        fun fromMember(member: Member): MemberProfileModel {
            return MemberProfileModel(
                member.fullName,
                member.locationString,
                member.avatarUrl,
                member.summary,
                member.skills
            )
        }
    }
}
