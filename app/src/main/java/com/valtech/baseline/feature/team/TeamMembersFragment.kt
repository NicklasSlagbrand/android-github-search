package com.valtech.baseline.feature.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.invisible
import com.valtech.baseline.core.extension.observeEvents
import com.valtech.baseline.core.extension.visible
import com.valtech.baseline.feature.base.BaseFragment
import com.valtech.baseline.feature.profile.MemberProfileModel
import com.valtech.baseline.feature.profile.ProfileActivity
import kotlinx.android.synthetic.main.team_memers_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeamMembersFragment : BaseFragment() {

    private val viewModel: TeamMembersViewModel by viewModel()

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
                is TeamMembersViewModel.Event.ShowTeam -> {
                    progressMembers.invisible()

                    val adapter = TeamMembersAdapter(requireContext()).apply {
                        clickListener = { member ->
                            val profileModel = MemberProfileModel.fromMember(member)
                            ProfileActivity.startActivity(requireContext(), profileModel)
                        }
                        results = it.members
                    }
                    rvTeamMembers.adapter = adapter
                    rvTeamMembers.visible()
                }
            }
        }
        observeEvents(viewModel.failure, ::handleFailure)
    }
}
