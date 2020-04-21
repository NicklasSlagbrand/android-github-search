package com.valtech.baseline.feature.repo.repoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.invisible
import com.valtech.baseline.core.extension.observeEvents
import com.valtech.baseline.core.extension.visible
import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.feature.base.BaseFragment
import com.valtech.baseline.feature.repo.ReposViewModel
import kotlinx.android.synthetic.main.team_memers_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposListFragment : BaseFragment() {
    private val viewModel: ReposViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.team_memers_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()

        viewModel.initialize()
    }

    private fun subscribeToLiveData() {
        observeEvents(viewModel.eventsLiveData) {
            when (it) {
                is ReposViewModel.Event.ShowRepos -> showMembersList(it.githubRepos)
            }
        }
        observeEvents(viewModel.failure, ::handleFailure)
    }

    private fun showMembersList(githubRepos: List<GithubRepo>) {

        val adapter = ReposAdapter(
            requireContext()
        ).apply {
            clickListener = { member ->
                viewModel.reposClicked(member)
                view?.findNavController()?.navigate(
                    R.id.action_reposHostFragment_to_reposListFragment22)
            }
            results = githubRepos
        }
        rvTeamMembers.adapter = adapter
        rvTeamMembers.visible()
    }
}
