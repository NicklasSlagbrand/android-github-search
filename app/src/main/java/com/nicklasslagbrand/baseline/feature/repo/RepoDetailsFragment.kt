package com.nicklasslagbrand.baseline.feature.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.core.extension.loadImageWithFitCenterTransform
import com.nicklasslagbrand.baseline.databinding.FragmentRepoDetailsBinding
import com.nicklasslagbrand.baseline.domain.model.GithubRepo

class RepoDetailsFragment : Fragment() {
    private var _binding: FragmentRepoDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: RepoDetailsFragmentArgs by navArgs()

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

        binding.ivAvatar.loadImageWithFitCenterTransform(args.repo.owner.avatarUrl?: "")
        binding.tvTitle.text = args.repo.title
        binding.tvDescription.text = args.repo.description

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
