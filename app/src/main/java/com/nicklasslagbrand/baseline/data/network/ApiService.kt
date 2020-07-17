package com.nicklasslagbrand.baseline.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createGithubApi(
    debug: Boolean = false,
    baseUrl: String,
    connectionChecker: NetworkConnectionChecker
): GithubApi {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(
            createOkHttpClient(
                debug = debug,
                networkConnectionChecker = connectionChecker
            )
        )
        .addConverterFactory(GsonConverterFactory.create(createNetworkResponseGson()))
        .build()
        .create(GithubApi::class.java)
}

private fun createOkHttpClient(
    debug: Boolean,
    networkConnectionChecker: NetworkConnectionChecker
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addNetworkInterceptor(NetworkConnectionInterceptor(networkConnectionChecker))
        addNetworkInterceptor(JsonRequestInterceptor())

        if (debug) {
            val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    println(message)
                }
            })
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            addInterceptor(loggingInterceptor)
        }
    }.build()
}

private fun createNetworkResponseGson(): Gson {
    return GsonBuilder()
        .registerTypeAdapter(DateTime::class.java, DateTimeDeserializer())
        .setLenient()
        .create()
}
