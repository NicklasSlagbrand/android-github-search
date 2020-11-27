package com.nicklasslagbrand.baseline.domain.dataSource

import androidx.paging.PageKeyedDataSource
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.repository.GithubRepository
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposDataSource(
    coroutineContext: CoroutineContext,
    val repository: GithubRepository,
    var onError: (Error) -> Unit
) : PageKeyedDataSource<Long, GithubRepo>() {
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, GithubRepo>
    ) {
        scope.launch {
            val result = withContext(coroutineContext) {
                repository.getAndroidRepos(DEFAULT_PAGE)
            }
            result.fold({
                callback.onResult(it, 1, DEFAULT_PAGE + 1)
            }, {
                onError(it)
            })
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GithubRepo>) {
        scope.launch {
            val result = withContext(coroutineContext) {
                repository.getAndroidRepos(params.key)

            }
            result.fold({
                callback.onResult(it, params.key + 1)
            }, {
                onError(it)
            })
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, GithubRepo>) {}

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    companion object {
        const val DEFAULT_PAGE: Long = 1
    }
}
