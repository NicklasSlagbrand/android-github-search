package com.nicklasslagbrand.searchactivity.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicklasslagbrand.core.BuildConfig
import com.nicklasslagbrand.core.Core
import com.nicklasslagbrand.core.repository.GithubRepository

class SearchViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            val repository = Core(context,BuildConfig.DEBUG).provideGithubRepository()
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}