package com.valtech.baseline.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.loadImageWithFitCenterTransform
import com.valtech.baseline.feature.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberProfileModel = arguments?.getParcelable<MemberProfileModel>(PROFILE_DATA_KEY)

        if (memberProfileModel == null) {
            activity?.finish()
        } else {
            initView(memberProfileModel)
        }
    }

    private fun initView(memberProfileModel: MemberProfileModel) {
        ivAvatar.loadImageWithFitCenterTransform(
            memberProfileModel.avatarUrl,
            RequestOptions.circleCropTransform()
        )

        tvName.text = memberProfileModel.fullName
        tvLocation.text = memberProfileModel.locationString
        tvSummary.text = memberProfileModel.summary

        createSkillsChips(memberProfileModel.skills)
    }

    private fun createSkillsChips(skills: List<String>) {
        skills.onEach {
            val chip = Chip(requireContext(), null, R.style.Chip_Skills)
            chip.text = it

            cgSkillsGroup.addView(chip)
        }
    }

    companion object {
        const val PROFILE_DATA_KEY = "profile_data_key"

        fun newInstance(memberProfileModel: MemberProfileModel) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PROFILE_DATA_KEY, memberProfileModel)
            }
        }
    }
}
