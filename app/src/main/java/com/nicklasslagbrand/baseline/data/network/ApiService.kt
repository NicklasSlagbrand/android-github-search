package com.nicklasslagbrand.baseline.data.network

import com.nicklasslagbrand.baseline.BuildConfig
import com.nicklasslagbrand.baseline.domain.model.RepoSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private fun createOkHttpClient(
    debug: Boolean,
    networkConnectionChecker: NetworkConnectionChecker
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addNetworkInterceptor(NetworkConnectionInterceptor(networkConnectionChecker))
        addNetworkInterceptor(JsonRequestInterceptor())

        if (debug) {
            val loggingInterceptor = HttpLoggingInterceptor { message -> println(message) }
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            addInterceptor(loggingInterceptor)
        }
    }.build()
}


/**
 * Github API communication setup via Retrofit.
 */
interface ApiService {
    /**
     * Get repos ordered by stars.
     */
    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse

    companion object {
        fun create(
            baseUrl: String,
            connectionChecker: NetworkConnectionChecker
        ): ApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = createOkHttpClient(
                debug = BuildConfig.DEBUG,
                networkConnectionChecker = connectionChecker
            )
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}