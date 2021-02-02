package com.nicklasslagbrand.feature.repo.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nicklasslagbrand.baseline.databinding.FragmentRepoDetailsBinding
import com.nicklasslagbrand.core.extension.loadImageWithFitCenterCircleCrop

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

        with(binding) {
            ivAvatar.loadImageWithFitCenterCircleCrop(args.repo.owner.avatarUrl)
            tvTitle.text = args.repo.name
            tvDescription.text = args.repo.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
