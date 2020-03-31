package com.valtech.baseline.domain.usecase

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.domain.error.Error

abstract class UseCase<Output : Any, in Params : Any> {
    abstract suspend fun call(params: Params): Result<Output, Error>

    object None
}
