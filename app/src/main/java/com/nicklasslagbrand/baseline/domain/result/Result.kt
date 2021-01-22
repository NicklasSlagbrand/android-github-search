package com.nicklasslagbrand.baseline.domain.result

import com.nicklasslagbrand.baseline.domain.result.Result.Failure
import com.nicklasslagbrand.baseline.domain.result.Result.Success

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}

internal inline fun <T> wrapResult(block: () -> T): Result<T> {
    return try {
        Success(block())
    } catch (exception: Exception) {
        Failure(exception)
    }
}
