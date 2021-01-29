package com.nicklasslagbrand.baseline.feature.repo

import androidx.paging.PagingSource
import com.nicklasslagbrand.core.entity.GithubRepo
import com.nicklasslagbrand.core.repository.GithubRepository
import com.nicklasslagbrand.core.result.Result

class RepoPagingSource(
    private val repository: GithubRepository,
    private val searchQuery: String,
) : PagingSource<Int, GithubRepo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> {
        try {
            val nextPage = params.key ?: 1
            val prevPage = if (nextPage == 1) null else nextPage - 1
            return when(val response = repository.getGithubReposBySearch(searchQuery,nextPage)) {
                is Result.Success -> {
                    LoadResult.Page(
                        data = response.data,
                        prevKey = prevPage,
                        nextKey = nextPage + 1
                    )
                }
                is Result.Failure -> {
                    LoadResult.Error(response.exception)
                }
            }
        } catch (e: Exception) { return LoadResult.Error(e) }
    }
}
