package com.example.myjoke.data.cloud

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitBuilder {

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(interceptor).build()
        )
        .build()

    protected open fun baseUrl(): String = "https://www.google.com"

    class RetrofitJoke : RetrofitBuilder() {
        override fun baseUrl(): String = "https://official-joke-api.appspot.com/"
    }

    class RetrofitQuote : RetrofitBuilder() {
        override fun baseUrl(): String = "https://api.quotable.io/"
    }
}