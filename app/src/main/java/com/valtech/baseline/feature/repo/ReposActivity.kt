package com.valtech.baseline.feature.repo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.valtech.baseline.R
import com.valtech.baseline.feature.base.BaseActivity
import com.valtech.baseline.feature.repo.repoList.ReposListFragment

class ReposActivity : BaseActivity() {
    override fun provideLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        findNavController(R.id.nav_host_fragment_container)
            .navigate(R.id.action_fragmentRepoHost_to_reposListFragment2)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, ReposActivity::class.java)
            )
        }
    }
}
