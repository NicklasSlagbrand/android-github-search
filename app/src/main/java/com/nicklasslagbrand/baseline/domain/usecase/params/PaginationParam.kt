package com.nicklasslagbrand.baseline.domain.usecase.params

data class PaginationParam(
    val limit: String,
    val offset: String
) {
    @SuppressWarnings("ThrowsCount")
    fun validate() {
        limit.ifBlank {
            throw IllegalArgumentException("'limit' should not be empty")
        }
        offset.ifBlank {
            throw IllegalArgumentException("'offset' should not be empty")
        }
    }
}
