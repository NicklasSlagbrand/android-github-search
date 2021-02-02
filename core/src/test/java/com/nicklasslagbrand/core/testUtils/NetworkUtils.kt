@file:Suppress("unused")

package com.nicklasslagbrand.core.testUtils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import java.nio.charset.Charset

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
