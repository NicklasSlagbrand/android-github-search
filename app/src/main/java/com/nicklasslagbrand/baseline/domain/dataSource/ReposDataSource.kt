package com.nicklasslagbrand.baseline.domain.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nicklasslagbrand.baseline.data.viewmodel.ConsumableEvent
import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import com.nicklasslagbrand.baseline.domain.usecase.GetRepoListUseCase
import com.nicklasslagbrand.baseline.domain.usecase.PagingParams
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposDataSource(
    val errorLiveData: MutableLiveData<ConsumableEvent<Error>>,
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
                callback.onResult(it, 1, DEFAULT_PAGE + 1)
            }, {
                errorLiveData.postValue(ConsumableEvent(it))
            })
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GithubRepo>) {
        scope.launch {
            val result = withContext(coroutineContext) {
                useCase.call(PagingParams(params.key))
            }
            result.fold({
                callback.onResult(it, params.key + 1)
            }, {
                errorLiveData.postValue(ConsumableEvent(it))
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
