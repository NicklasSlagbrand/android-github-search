package com.nicklasslagbrand.baseline.domain.usecase

import com.nicklasslagbrand.baseline.domain.result.Result
import com.nicklasslagbrand.baseline.domain.error.Error

abstract class UseCase<Output : Any, in Params : Any> {
    abstract suspend fun call(params: Params): Result<Output, Error>

    object None
}
