package com.nicklasslagbrand.baseline.testutils

import java.nio.charset.Charset
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer

fun MockWebServer.init(): String {
    start()

    return this.url("").toString()
}

fun MockWebServer.takeSecondRequest(): RecordedRequest {
    // Skip first request
    this.takeRequest()

    return this.takeRequest()
}

fun successFromFile(fileName: String): MockResponse {
    val buffer = Buffer().readFrom(
        ClassLoader.getSystemClassLoader()
            .getResourceAsStream(fileName)
    )
    return MockResponse().setBody(buffer)
}

fun textFromFile(fileName: String): String {
    return Buffer().readFrom(
        ClassLoader.getSystemClassLoader()
            .getResourceAsStream(fileName)
    ).readString(Charset.defaultCharset())
}
