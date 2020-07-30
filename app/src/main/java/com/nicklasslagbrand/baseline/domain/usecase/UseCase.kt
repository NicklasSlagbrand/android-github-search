package com.nicklasslagbrand.baseline.domain.usecase

import com.nicklasslagbrand.baseline.domain.error.Error
import com.nicklasslagbrand.baseline.domain.result.Result

abstract class UseCase<Output : Any, in Params : Any> {
    abstract suspend fun call(params: Params): Result<Output, Error>

    object None
}
