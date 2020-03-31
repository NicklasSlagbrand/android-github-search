package com.valtech.baseline.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.observeEvents
import com.valtech.baseline.feature.base.BaseActivity
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel.Event.ShowExchangeView
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel.Event.ShowMessageView
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel.Event.ShowNetworkView
import com.valtech.baseline.feature.navigation.NavigationMenuViewModel.Event.ShowTeamView
import com.valtech.baseline.feature.team.TeamMembersFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
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
        navigation.itemIconTintList = null

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_team -> {
                    navigationMenuViewModel.showTeamView()
                    true
                }
                R.id.action_exchange -> {
                    navigationMenuViewModel.showExchangeView()
                    true
                }
                R.id.action_network -> {
                    navigationMenuViewModel.showNetworkView()
                    true
                }
                R.id.action_message -> {
                    navigationMenuViewModel.showMessageView()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun subscribeLiveData() {
        observeEvents(navigationMenuViewModel.eventsLiveData) {
            when (it) {
                is ShowTeamView -> {
                    switchFragment(ScreenViewType.TeamView)
                }
                is ShowExchangeView -> {
                    switchFragment(ScreenViewType.ExchangeView)
                }
                is ShowNetworkView -> {
                    switchFragment(ScreenViewType.NetworkView)
                }
                is ShowMessageView -> {
                    switchFragment(ScreenViewType.MessageView)
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
        ScreenViewType.TeamView -> TeamMembersFragment()
        ScreenViewType.ExchangeView -> PlaceholderFragment()
        ScreenViewType.NetworkView -> PlaceholderFragment()
        ScreenViewType.MessageView -> PlaceholderFragment()
    }

    enum class ScreenViewType {
        TeamView, ExchangeView, MessageView, NetworkView
    }

    companion object {
        private const val SHOULD_NAVIGATE_TO_POSITION = "navigate_to_position"

        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, MainActivity::class.java).putExtra(SHOULD_NAVIGATE_TO_POSITION, false)
            )
        }

        fun returnToActivity(context: Context, shouldGoToCards: Boolean = false) {
            context.startActivity(
                returnToActivityIntent(
                    context,
                    shouldGoToCards
                )
            )
        }

        private fun returnToActivityIntent(context: Context, shouldGoToCards: Boolean = false): Intent {
            return Intent(context, MainActivity::class.java)
                .putExtra(SHOULD_NAVIGATE_TO_POSITION, false)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
    }
}
