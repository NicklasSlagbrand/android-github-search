package com.valtech.baseline.feature.repoDetails

import android.os.Bundle
import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.loadImageWithFitCenterTransform
import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.feature.base.BaseFragment
import com.valtech.baseline.feature.repoList.ReposViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RepoDetailsFragment : BaseFragment() {
    private val viewModel: ReposViewModel by sharedViewModel()
    private lateinit var githubRepo: GithubRepo

    override fun provideLayoutId()= R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        githubRepo = viewModel.activeGithubRepo

        ivAvatar.loadImageWithFitCenterTransform(
            githubRepo.owner.avatarUrl?: "",
            RequestOptions.circleCropTransform()
        )

        tvTitle.text = githubRepo.title
        tvDescription.text = githubRepo.description

//        createSkillsChips(githubRepo.skills)
    }

    private fun createSkillsChips(skills: List<String>) {
        skills.onEach {
            val chip = Chip(requireContext(), null, R.style.Chip_Skills)
            chip.text = it

            cgSkillsGroup.addView(chip)
        }
    }
}
