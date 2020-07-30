package com.nicklasslagbrand.baseline.feature.repo.repoList

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.core.extension.observe
import com.nicklasslagbrand.baseline.core.extension.observeEvents
import com.nicklasslagbrand.baseline.feature.base.BaseFragment
import com.nicklasslagbrand.baseline.feature.repo.ReposViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposListFragment : BaseFragment() {
    private val reposAdapter: ReposAdapter = ReposAdapter()

    private val viewModel: ReposViewModel by sharedViewModel()
    override fun provideLayoutId(): Int? = R.layout.fragment_repo_list

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
        observeEvents(viewModel.failure, ::handleFailure)
    }

    private fun navigateToDetails() {
        findNavController().navigate(
            R.id.action_reposListFragment_to_repoDetailsFragment)
    }
}
