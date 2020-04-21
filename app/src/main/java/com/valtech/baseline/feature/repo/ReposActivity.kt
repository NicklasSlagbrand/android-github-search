package com.valtech.baseline.feature.repo

import android.content.Context
import android.content.Intent
import com.valtech.baseline.R
import com.valtech.baseline.feature.base.BaseActivity

class ReposActivity : BaseActivity() {
    override fun provideLayoutId() = R.layout.activity_main

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, ReposActivity::class.java)
            )
        }
    }
}
