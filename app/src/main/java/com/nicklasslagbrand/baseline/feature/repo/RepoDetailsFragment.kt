package com.nicklasslagbrand.baseline.feature.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.core.extension.loadImageWithFitCenterTransform
import com.nicklasslagbrand.baseline.databinding.FragmentRepoDetailsBinding
import com.nicklasslagbrand.baseline.domain.model.GithubRepo

class RepoDetailsFragment : Fragment() {
    private var _binding: FragmentRepoDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exampleTags = listOf("Android", "Github", "Test")

        arguments?.run {
            val githubRepo = getParcelable<GithubRepo>("githubRepo")
            if (githubRepo != null) {
                binding.ivAvatar.loadImageWithFitCenterTransform(githubRepo.owner.avatarUrl?: "")
                binding.tvTitle.text = githubRepo.title
                binding.tvDescription.text = githubRepo.description
            } else {
                //TODO: handle error
            }
        }


        createSkillsChips(exampleTags)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createSkillsChips(skills: List<String>) {
        skills.onEach {
            val chip = Chip(requireContext(), null, R.style.Chip_Skills)
            chip.text = it

            binding.cgSkillsGroup.addView(chip)
        }
    }
}
