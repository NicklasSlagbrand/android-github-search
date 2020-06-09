package com.valtech.baseline.feature.repo.repoList

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.invisible
import com.valtech.baseline.core.extension.observeEvents
import com.valtech.baseline.core.extension.visible
import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.feature.base.BaseFragment
import com.valtech.baseline.feature.repo.ReposViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposListFragment : BaseFragment() {
    private val viewModel: ReposViewModel by sharedViewModel()
    override fun provideLayoutId(): Int? = R.layout.fragment_repo_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        viewModel.initialize()
    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.eventsLiveData) {
            when (it) {
                is ReposViewModel.Event.ShowRepos -> showMembersList(it.githubRepos)
                is ReposViewModel.Event.ShowRepoDetails -> navigateToRepoDetails()
            }
        }
        observeEvents(viewModel.failure, ::handleFailure)
    }

    private fun navigateToRepoDetails(){

    }

    private fun showMembersList(githubRepos: List<GithubRepo>) {
        pbRepo.invisible()
        rvTeamMembers.adapter = ReposAdapter(requireContext()).apply {
            results = githubRepos
            clickListener = { member ->
                viewModel.reposClicked(member)
                navigateToDetails()
            }
        }
        rvTeamMembers.visible()
    }

    private fun navigateToDetails() {
        findNavController().navigate(
            R.id.action_reposListFragment_to_repoDetailsFragment)
    }
}
