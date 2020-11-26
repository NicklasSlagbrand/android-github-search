package com.nicklasslagbrand.baseline.feature.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.core.extension.observe
import com.nicklasslagbrand.baseline.core.extension.observeEvents
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposListFragment : Fragment(R.layout.fragment_repo_list) {
    private val reposAdapter: ReposAdapter = ReposAdapter()

    private val viewModel: ReposViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        initializeList()
    }
    private fun initializeList() {
        rvRepos.layoutManager = LinearLayoutManager(requireContext())
        rvRepos.adapter = reposAdapter.apply {
            clickListener = {
                viewModel.itemClicked(it)
            }
        }
    }

    private fun subscribeToLiveData() {
        observe(viewModel.getReposList()) {
            reposAdapter.submitList(it)
        }

        observeEvents(viewModel.eventsLiveData) {
            when (it) {
                is ReposViewModel.Event.ShowRepoDetails -> navigateToDetails()
            }
        }
    }

    private fun navigateToDetails() {
        findNavController().navigate(
            R.id.action_reposListFragment_to_repoDetailsFragment)
    }
}
