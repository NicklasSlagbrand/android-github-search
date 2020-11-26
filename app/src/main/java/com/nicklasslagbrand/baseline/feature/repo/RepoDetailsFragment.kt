package com.nicklasslagbrand.baseline.feature.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.core.extension.loadImageWithFitCenterTransform
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import kotlinx.android.synthetic.main.fragment_repo_details.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RepoDetailsFragment : Fragment(R.layout.fragment_repo_details) {
    private val viewModel: ReposViewModel by sharedViewModel()
    private lateinit var githubRepo: GithubRepo


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        githubRepo = viewModel.activeGithubRepo

        ivAvatar.loadImageWithFitCenterTransform(
            githubRepo.owner.avatarUrl ?: "",
            RequestOptions.circleCropTransform()
        )

        tvTitle.text = githubRepo.title
        tvDescription.text = githubRepo.description

        val exampleTags = listOf("Android", "Github", "Test")

        createSkillsChips(exampleTags)
    }

    private fun createSkillsChips(skills: List<String>) {
        skills.onEach {
            val chip = Chip(requireContext(), null, R.style.Chip_Skills)
            chip.text = it

            cgSkillsGroup.addView(chip)
        }
    }
}
