package com.valtech.baseline.feature.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.invisible
import com.valtech.baseline.core.extension.observeEvents
import com.valtech.baseline.core.extension.visible
import com.valtech.baseline.domain.model.GithubRepo
import com.valtech.baseline.feature.base.BaseFragment
import com.valtech.baseline.feature.repo.repoList.ReposAdapter
import kotlinx.android.synthetic.main.team_memers_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReposHostFragment : BaseFragment() {
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
                is ReposViewModel.Event.ShowRepos -> {}
            }
        }
        observeEvents(viewModel.failure, ::handleFailure)
    }
}
