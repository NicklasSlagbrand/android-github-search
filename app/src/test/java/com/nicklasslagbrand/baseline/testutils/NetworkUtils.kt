@file:Suppress("unused")

package com.nicklasslagbrand.baseline.testutils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer

fun MockWebServer.init(): String {
    start()

    return this.url("").toString()
}

fun successFromFile(fileName: String): MockResponse {

    val buffer = Buffer().readFrom(
        ClassLoader.getSystemClassLoader()
            .getResourceAsStream(fileName)
    )
    return MockResponse().setBody(buffer)
}