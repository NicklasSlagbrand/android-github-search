package com.nicklasslagbrand.searchactivity.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nicklasslagbrand.searchactivity.R
import com.nicklasslagbrand.searchactivity.databinding.ActivitySearchResultsBinding

class SearchResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var viewModelFactory: SearchViewModelFactory
    private lateinit var searchQuery: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        viewModelFactory = SearchViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
        setContentView(binding.root)
        binding.shimmerViewContainer.startShimmer()

        handleIntent(intent)
        setupObservers()
        binding.tvSearchQuery.text = searchQuery
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupObservers() {
        viewModel.repoLiveData.observe(this, {
            binding.rvResult.adapter = SearchResultAdapter().apply {
                result = it
                clickListener = {

                }
            }
            binding.tvResultMessage.text = getString(R.string.results_message,it.size)
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
        })
        viewModel.getResult(searchQuery)
    }

    private fun handleIntent(intent: Intent) {
        searchQuery = intent.getStringExtra(SearchManager.QUERY).also { query ->
            SearchRecentSuggestions(this, MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE)
                .saveRecentQuery(query, null)
        }?: ""
    }
}