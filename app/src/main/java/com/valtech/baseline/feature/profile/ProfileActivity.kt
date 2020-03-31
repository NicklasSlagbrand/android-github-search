package com.valtech.baseline.feature.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.valtech.baseline.R
import com.valtech.baseline.feature.profile.ProfileFragment.Companion.PROFILE_DATA_KEY
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val profileModel = intent.getParcelableExtra<MemberProfileModel>(PROFILE_DATA_KEY)

            if (profileModel == null) {
                Timber.e("Intent doesn't contain `PROFILE_DATA_KEY` extra.")
                finish()
            }

            title = profileModel.fullName

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProfileFragment.newInstance(profileModel))
                .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun startActivity(context: Context, profileModel: MemberProfileModel) {
            context.startActivity(
                Intent(context, ProfileActivity::class.java)
                    .putExtra(PROFILE_DATA_KEY, profileModel)
            )
        }
    }
}
