package com.nicklasslagbrand.searchactivity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.nicklasslagbrand.searchactivity.databinding.ActivityMainBinding
import com.nicklasslagbrand.searchactivity.search.SearchResultsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
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