package com.nicklasslagbrand.feature.search

import android.content.SearchRecentSuggestionsProvider

class MySuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.nicklasslagbrand.MySuggestionProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}