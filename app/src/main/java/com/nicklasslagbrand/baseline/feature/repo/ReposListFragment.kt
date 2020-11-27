package com.nicklasslagbrand.baseline.feature.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.core.extension.observe
import com.nicklasslagbrand.baseline.core.extension.observeEvents
import com.nicklasslagbrand.baseline.databinding.FragmentRepoListBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposListFragment : Fragment() {
    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding!!

    private val reposAdapter: ReposAdapter = ReposAdapter()
    private val viewModel: ReposViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        initializeList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initializeList() {
        binding.rvRepos.adapter = reposAdapter.apply {
            clickListener = {
                viewModel.itemClicked(it)
            }
        }
    }

    private fun subscribeToLiveData() {
        observe(viewModel.getReposList()) {
            reposAdapter.submitList(it)
        }

        observeEvents(viewModel.eventLiveData) {
            when(it) {
                is ReposViewModel.Event.ShowRepoDetails -> {
                    val action = ReposListFragmentDirections.actionListToDetails(it.repo)
                    findNavController().navigate(action)
                }
                is ReposViewModel.Event.OnError -> {
                    Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}
