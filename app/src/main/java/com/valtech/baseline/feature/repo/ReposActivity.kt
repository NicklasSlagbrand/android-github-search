package com.valtech.baseline.feature.repo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.valtech.baseline.R
import com.valtech.baseline.feature.base.BaseActivity
import com.valtech.baseline.feature.repo.repoList.ReposListFragment

class ReposActivity : BaseActivity() {
    override fun provideLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        switchFragment(ScreenViewType.StartView)
    }

    private fun switchFragment(screenViewType: ScreenViewType) {
            val f = getItem(screenViewType)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contentFragment, f)
            transaction.commit()
        }

    private fun getItem(screenViewType: ScreenViewType) = when (screenViewType) {
        ScreenViewType.StartView -> ReposListFragment()
    }

    enum class ScreenViewType {
        StartView
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, ReposActivity::class.java)
            )
        }
    }
}
