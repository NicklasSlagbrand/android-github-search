package com.valtech.baseline.feature.repoList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.observeEvents
import com.valtech.baseline.feature.base.BaseActivity
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel.Event.ShowTeamView
import com.valtech.baseline.feature.repoDetails.RepoDetailsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ReposActivity : BaseActivity() {
    private val navigationMenuViewModel: NavigationMenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeLiveData()
        intiViews()

        navigationMenuViewModel.initialize()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        navigationMenuViewModel.onBackToScreen(false)
    }

    private fun intiViews() {
        navigationMenuViewModel.showTeamView()
    }

    private fun subscribeLiveData() {
        observeEvents(navigationMenuViewModel.eventsLiveData) {
            when (it) {
                is ShowTeamView -> {
                    switchFragment(ScreenViewType.TeamView)
                }
            }
        }
    }

    private fun switchFragment(screenViewType: ScreenViewType) {
        val f = getItem(screenViewType)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentFragment, f)
        transaction.commit()
    }

    private fun getItem(screenViewType: ScreenViewType) = when (screenViewType) {
        ScreenViewType.TeamView -> ReposFragment()
        ScreenViewType.ProfileView -> RepoDetailsFragment()
    }

    enum class ScreenViewType {
        TeamView, ProfileView
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, ReposActivity::class.java)
            )
        }
    }
}
