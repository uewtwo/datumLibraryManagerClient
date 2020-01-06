package com.example.datumlibrarymanager.api

import com.example.datumlibrarymanager.model.SampleResponse
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

open class ApiClientManager {
    companion object {
        private const val ENDPOINT = "http://zipcloud.ibsnet.co.jp/"
        val adapter = Moshi.Builder().build().adapter(SampleResponse::class.java)

        val apiClient: ApiInterface = Retrofit.Builder()
            .client(getClient())
            .baseUrl(ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        private fun getClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
    }
}
