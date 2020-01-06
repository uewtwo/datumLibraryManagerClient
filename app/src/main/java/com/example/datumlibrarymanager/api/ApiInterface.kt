package com.example.datumlibrarymanager.api

import com.example.datumlibrarymanager.model.SampleResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


// TODO: 初期化コストが重い(らしい)ので，グローバル変数として持つ


interface ApiInterface {
    @GET("api/search")
    fun getZip(@Query("zipcode") zipcode: String) : Observable<SampleResponse>

    // TODO: API分ここで定義する
}

