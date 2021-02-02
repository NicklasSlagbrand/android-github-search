package com.nicklasslagbrand.feature

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.databinding.ActivityMainBinding
import com.nicklasslagbrand.feature.search.SearchResultsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.action_search).actionView as SearchView)

        searchView.apply {
            setSearchableInfo(
                searchManager.getSearchableInfo(
                    ComponentName(
                        this@MainActivity,
                        SearchResultsActivity::class.java
                    )
                )
            )
            maxWidth = Integer.MAX_VALUE
        }
        return true
    }
}