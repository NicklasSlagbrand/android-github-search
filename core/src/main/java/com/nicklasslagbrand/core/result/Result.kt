package com.nicklasslagbrand.core.result

import com.nicklasslagbrand.core.exception.NoNetworkConnectionException
import com.nicklasslagbrand.core.result.Result.Failure
import com.nicklasslagbrand.core.result.Result.Success
import java.lang.Exception

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}

internal inline fun <T> wrapResult(block: () -> T): Result<T> {
    return try {
        Success(block())
    }catch (exception: NoNetworkConnectionException) {
        Failure(exception)
    }
}
