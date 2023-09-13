package com.example.myjoke.data.cloud

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://official-joke-api.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}