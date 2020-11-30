@file:Suppress("unused")

package com.nicklasslagbrand.baseline.testutils

import android.content.Context
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import java.io.InputStream
import java.io.InputStreamReader

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

fun successFromFile2(context: Context,fileName: String): MockResponse {

    val buffer = Buffer().readFrom(context.assets.open(fileName)
    )
    return MockResponse().setBody(buffer)
}
private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
    val builder = StringBuilder()
    val reader = InputStreamReader(inputStream, charsetName)
    reader.readLines().forEach {
        builder.append(it)
    }
    return builder.toString()
}
