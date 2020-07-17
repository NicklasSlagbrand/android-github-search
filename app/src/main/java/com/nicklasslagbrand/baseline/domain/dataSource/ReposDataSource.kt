package com.nicklasslagbrand.baseline.domain.dataSource

import androidx.paging.PageKeyedDataSource
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.baseline.domain.usecase.PagingParams
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ReposDataSource(
    coroutineContext: CoroutineContext,
    val useCase: GetRepoListUseCase
) : PageKeyedDataSource<Long, GithubRepo>() {

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, GithubRepo>
    ) {
        scope.launch {
            val result = withContext(coroutineContext) {
                useCase.call(PagingParams(DEFAULT_PAGE))
            }
            result.fold({
                callback.onResult(it, 1, DEFAULT_PAGE+1)
            }, {

            })
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GithubRepo>) {
        scope.launch {
            val result = withContext(coroutineContext) {
                useCase.call(PagingParams(params.key))
            }
            result.fold({
                callback.onResult(it, params.key+1)
            }, {

            })
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, GithubRepo>) {}

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    companion object{
        final const val DEFAULT_PAGE: Long = 1
    }
}